import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension

import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.file.impl.DefaultFileMetadata.file
import org.gradle.kotlin.dsl.*

import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import java.io.File

class CommonModulePlugin : Plugin<Project> {

    private val isApplicationModule: Boolean
        //get() = defaultConfig is AppExtension
        get() = moduleName == "app"

    private lateinit var moduleName: String

    override fun apply(project: Project) {
        moduleName = project.name

        printModuleASCIIArt(moduleName)

        project.plugins.apply {
            apply("kotlin-android")
            apply("kotlin-kapt")
            apply("kotlin-parcelize")
            //apply(BuildScript.JUNIT5_PLUGIN)
            apply(BuildScript.KTLINT_PLUGIN)

            if (moduleName == "data")
                apply("kotlinx-serialization")
        }

        //project.applyKtLintConfigurations()

        val androidExtension = project.extensions.getByName("android") as? BaseExtension ?: return

        project.applyModuleConfigurations(androidExtension)

        androidExtension.applyAndroidConfigurations()
        androidExtension.applyProguardConfigurations()

        if (isApplicationModule)
            androidExtension.enableJava11(project)
        else
            androidExtension.enableJava8(project)

    }

    private fun printModuleASCIIArt(moduleName: String) {
        println("${ASCIIArt.A_GUY_SMASHING_COMPUTER}\n\n\n\t\t\t\t\tBuilding $moduleName module...\n")
    }

    private fun Project.applyKtLintConfigurations() {
        val ktLint = project.extensions.getByName("ktlint")
        println("ktLint is ${ktLint.javaClass.simpleName}")

        // Run `ktlintCheck` task after each module prebuild
        afterEvaluate {
            tasks {
                val ktLintCheck = tasks.named("ktlintCheck")
                /*val assembleDebug = tasks.named(if (isApplicationModule) "assembleGmsDebug" else "assembleDebug")

                assembleDebug {
                    dependsOn(ktLintCheck)
                }*/

                "preBuild" {
                    dependsOn(ktLintCheck)
                }
            }
        }

        ktlint {
            //version.set("0.38.1")
            android.set(true)
            coloredOutput.set(true)
            //debug.set(true)
            enableExperimentalRules.set(true)
            verbose.set(true)
            ignoreFailures.set(true)
            outputToConsole.set(true)
            disabledRules.set(
                setOf(
                    "colon-spacing",
                    "experimental:multiline-if-else",
                    "no-blank-line-before-rbrace",
                    "no-wildcard-imports",
                    "import-ordering"
                )
            )
            filter {
                exclude("**/generated/**")
                include("**/kotlin/**")
            }
            reporters {
                //reporter(ReporterType.PLAIN)
                //reporter(ReporterType.CHECKSTYLE)
                reporter(ReporterType.HTML)
                reporter(ReporterType.JSON)
            }
        }
    }

    private fun Project.applyModuleConfigurations(androidExtension: BaseExtension) {
        modulePluginExtension = project.extensions.create(
            "moduleConfigurations",
            CommonModulePluginExtension::class
        )

        project.afterEvaluate {
            println("useJUnitRunner5 is ${modulePluginExtension?.useJUnitRunner5}")

            with(modulePluginExtension!!) {
                if (useJUnitRunner5) {
                    with(androidExtension) {
                        //project.plugins.apply(BuildScript.JUNIT5_PLUGIN)
//                        defaultConfig.testInstrumentationRunnerArgument(
//                            "runnerBuilder",
//                            "de.mannodermaus.junit5.AndroidJUnit5Builder"
//                        )
                    }
                }

                if (useRoboelectric) {
                    dependencies.apply {
                        androidInstrumentationTest(useJUnitRunner5, useRoboelectric)
                        roboelectricTest()
                    }

                    tasks {
                        val dependencies = getByName("dependencies")

                        "preBuild" {
                            dependsOn(dependencies)
                        }
                    }
                }
            }
        }
    }

    private fun BaseExtension.applyAndroidConfigurations() {
        buildToolsVersion(AppConfig.BUILD_TOOLS_VERSION)
        compileSdkVersion(AppConfig.COMPILE_SDK)

        defaultConfig {
            if (isApplicationModule)
                applicationId = AppConfig.APPLICATION_ID

            versionCode = AppConfig.VERSION_CODE
            versionName = AppConfig.VERSION_NAME
            minSdkVersion(AppConfig.MINIMUM_SDK)
            targetSdkVersion(AppConfig.TARGET_SDK)

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            //testInstrumentationRunnerArgument("runnerBuilder", "de.mannodermaus.junit5.AndroidJUnit5Builder")
        }

        if (isApplicationModule) {
            println("$moduleName - Creating build variants")

            flavorDimensions("platform")
            productFlavors {
                create("gms") { dimension("platform") }
                create("hms") { dimension("platform") }
            }
        }

        signingConfigs {
            /*create("releaseConfig") {
                keyAlias = "key0"
                keyPassword = "123456"
                storeFile = File("key.jks")
                storePassword = "123456"
            }*/
        }

        sourceSets {
            getByName("main") {
                java.srcDir("src/main/kotlin")
                resources.srcDir("src/main/res")
            }

            getByName("test") {
                java.srcDir("src/test/kotlin")
                resources.srcDir("src/test/res")
            }

            getByName("androidTest") {
                java.srcDir("src/androidTest/kotlin")
                resources.srcDir("src/androidTest/res")
            }
        }

        packagingOptions {
            resources.excludes.apply {
                add("META-INF/LICENSE*")
                add("META-INF/AL2.0")
                add("META-INF/LGPL2.1")
            }

            /*pickFirst("META-INF/DEPENDENCIES")
            pickFirst("META-INF/ASL2.0")*/

            //exclude("META-INF/DEPENDENCIES")
            /*exclude("META-INF/LICENSE")
            exclude("META-INF/LICENSE.txt")
            exclude("META-INF/license.txt")
            exclude("META-INF/NOTICE")
            exclude("META-INF/NOTICE.txt")
            exclude("META-INF/notice.txt")*/
            //exclude("META-INF/ASL2.0")
            //exclude("META-INF/*.kotlin_module")
        }
    }

    private fun BaseExtension.applyProguardConfigurations() {
        println("ProguardConfigurations are applying...")

        val proguardFile = "proguard-rules.pro"

        when (this) {
            is AppExtension -> buildTypes {
                getByName("debug") {
                    //signingConfig = signingConfigs.getByName("releaseConfig")
                    isDebuggable = true
                }
                getByName("release") {
                    isMinifyEnabled = true
                    isShrinkResources = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        proguardFile
                    )
                    //signingConfig = signingConfigs.getByName("releaseConfig")
                }
            }
            is LibraryExtension -> defaultConfig { consumerProguardFiles(proguardFile) }
        }
    }

    private fun BaseExtension.enableJava8(project: Project) {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        project.tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                //freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check")
                jvmTarget = "1.8"
                //useIR = true
            }
        }
    }

    private fun BaseExtension.enableJava11(project: Project) {
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        project.tasks.withType<KotlinCompile>().configureEach {
            kotlinOptions {
                jvmTarget = "11"
                //useIR = true
            }
        }
    }

    /**
     * Configures the [ktlint][org.jlleitschuh.gradle.ktlint.KtlintExtension] extension.
     */
    private fun Project.`ktlint`(configure: Action<KtlintExtension>): Unit =
        (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("ktlint", configure)

    companion object {
        var modulePluginExtension: CommonModulePluginExtension? = null
    }

}