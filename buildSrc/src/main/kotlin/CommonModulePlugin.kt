import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class CommonModulePlugin : Plugin<Project> {

    private val isApplicationModule: Boolean
        get() = moduleName == "app"

    private lateinit var moduleName: String

    override fun apply(project: Project) {
        moduleName = project.name

        printModuleASCIIArt(moduleName)

        project.plugins.apply {
            apply("kotlin-android")
            apply("kotlin-kapt")
            apply("kotlin-parcelize")

            if (moduleName == "data")
                apply("kotlinx-serialization")
        }

        val androidExtension = project.extensions.getByName("android") as? BaseExtension ?: return

        project.applyModuleConfigurations()

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

    private fun Project.applyModuleConfigurations() {
        modulePluginExtension = project.extensions.create(
            "moduleConfigurations",
            CommonModulePluginExtension::class
        )

        project.afterEvaluate {
            with(modulePluginExtension!!) {
                if (useRoboelectric) {
                    dependencies.apply {
                        androidInstrumentationTest()
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
            minSdk = AppConfig.MINIMUM_SDK
            targetSdk = AppConfig.TARGET_SDK

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        }
    }

    private fun BaseExtension.applyProguardConfigurations() {
        println("ProguardConfigurations are applying...")

        val proguardFile = "proguard-rules.pro"

        when (this) {
            is AppExtension -> buildTypes {
                getByName("debug") {
                    isDebuggable = true
                }
                getByName("release") {
                    isMinifyEnabled = true
                    isShrinkResources = true
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        proguardFile
                    )
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
                jvmTarget = "1.8"
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
            }
        }
    }

    companion object {
        var modulePluginExtension: CommonModulePluginExtension? = null
    }

}