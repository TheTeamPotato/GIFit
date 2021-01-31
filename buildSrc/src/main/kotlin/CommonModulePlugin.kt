import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class CommonModulePlugin : Plugin<Project> {

    private val BaseExtension.isApplicationModule: Boolean
        get() = defaultConfig is AppExtension

    override fun apply(project: Project) {
        project.plugins.apply {
            apply("kotlin-android")
            apply("kotlin-kapt")
            apply("kotlin-parcelize")
        }

        val androidExtension = project.extensions.getByName("android")
        if (androidExtension is BaseExtension) {
            androidExtension.applyAndroidConfigurations()
            androidExtension.applyProguardConfigurations()
            androidExtension.enableJava11(project)
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
        }

        flavorDimensions("platform")
        productFlavors {
            create("gms") { dimension("platform") }
            create("hms") { dimension("platform") }
        }

        sourceSets {
            getByName("main") {
                java.srcDir("src/main/kotlin")
                resources.srcDir("src/main/res")
            }
        }
    }

    private fun BaseExtension.applyProguardConfigurations() {
        val proguardFile = "proguard-rules.pro"

        when (this) {
            is AppExtension -> buildTypes {
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
                //freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check")
                jvmTarget = "1.8"
                useIR = true
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
                useIR = true
            }
        }
    }

}