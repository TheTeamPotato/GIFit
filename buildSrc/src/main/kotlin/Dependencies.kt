import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

const val ANDROID_APPLICATION = "com.android.application"
const val ANDROID_LIBRARY = "com.android.library"
const val DAGGER_HILT_ANDROID_PLUGIN = "dagger.hilt.android.plugin"
const val COMMON_MODULE_PLUGIN = "common-module-plugin"

object BuildScript {
    private const val ANDROID_GRADLE_VERSION = "7.0.0-alpha05"
    private const val ANDROID_HILT_GRADLE_VERSION = "2.31.2-alpha"
    private const val APP_GALLERY_CONNECT_VERSION = "1.4.2.301"
    private const val KOTLIN_VERSION = "1.4.21"

    const val VERSIONS_PLUGIN_VERSION = "0.36.0"
    const val VERSIONS_PLUGIN = "com.github.ben-manes.versions"

    const val ANDROID_GRADLE_PLUGIN = "com.android.tools.build:gradle:$ANDROID_GRADLE_VERSION"
    const val ANDROID_HILT_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:$ANDROID_HILT_GRADLE_VERSION"
    const val APP_GALLERY_CONNECT_PLUGIN = "com.huawei.agconnect:agcp:$APP_GALLERY_CONNECT_VERSION"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
}

object Modules {
    const val DATA_ANDROID = ":data-android"
    const val BASE = ":base"

    object Features {
        const val SPLASH = ":features:splash"
    }

    object Layers {
        const val DATA = ":layers:data"
        const val DOMAIN = ":layers:domain"
        const val UI = ":layers:ui"
    }
}

object Libraries {
    private const val ACCOMPANIST_VERSION = "0.4.0"
    private const val MATERIAL_DESIGN_VERSION = "1.3.0-rc01"
    private const val PERMISSION_DISPATCHER_VERSION = "4.8.0"
    private const val SHOWKASE_VERSION = "1.0.0-alpha03"
    private const val TIMBER_VERSION = "4.7.1"

    const val ACCOMPANIST_COIL = "dev.chrisbanes.accompanist:accompanist-coil:$ACCOMPANIST_VERSION"
    const val MATERIAL_DESIGN = "com.google.android.material:material:$MATERIAL_DESIGN_VERSION"
    const val PERMISSION_DISPATCHER = "org.permissionsdispatcher:permissionsdispatcher:$PERMISSION_DISPATCHER_VERSION"
    const val PERMISSION_DISPATCHER_PROCESSOR = "org.permissionsdispatcher:permissionsdispatcher-processor:$PERMISSION_DISPATCHER_VERSION"
    const val SHOWKASE = "com.airbnb.android:showkase:$SHOWKASE_VERSION"
    const val SHOWKASE_COMPILER = "com.airbnb.android:showkase-processor:$SHOWKASE_VERSION"
    const val TIMBER = "com.jakewharton.timber:timber:$TIMBER_VERSION"

    object AndroidX {
        private const val APPCOMPAT_VERSION = "1.3.0-beta01"
        private const val CORE_VERSION = "1.5.0-beta01"
        private const val FRAGMENT_KTX_VERSION = "1.2.5"
        private const val MULTIDEX_VERSION = "2.0.1"
        private const val NAVIGATION_COMPONENT_VERSION = "2.3.1"
        private const val LIFECYCLE_VERSION = "2.2.0"
        private const val ROOM_VERSION = "2.3.0-beta01"

        const val APPCOMPAT = "androidx.appcompat:appcompat:$APPCOMPAT_VERSION"
        const val CORE = "androidx.core:core:$CORE_VERSION"
        const val CORE_KTX = "androidx.core:core-ktx:$CORE_VERSION"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:$FRAGMENT_KTX_VERSION"
        const val MULTIDEX = "androidx.multidex:multidex:$MULTIDEX_VERSION"
        const val NAVIGATION_COMPONENT = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_COMPONENT_VERSION"
        const val NAVIGATION_COMPONENT_UI = "androidx.navigation:navigation-ui-ktx:$NAVIGATION_COMPONENT_VERSION"
        const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:$LIFECYCLE_VERSION"
        const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_VERSION"
        const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION"
        const val ROOM = "androidx.room:room-runtime:$ROOM_VERSION"
        const val ROOM_KTX = "androidx.room:room-ktx:$ROOM_VERSION"
        const val ROOM_COMPILER = "androidx.room:room-compiler:$ROOM_VERSION"
    }

    object Compose {
        private const val COMPOSE_NAVIGATION_VERSION = "1.0.0-alpha03"
        private const val COMPOSE_UI_TOOLING_VERSION = "1.0.0-alpha08"
        private const val COMPOSE_VERSION = "1.0.0-alpha08"

        const val FOUNDATION = "androidx.compose.foundation:foundation:$COMPOSE_VERSION" // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
        const val MATERIAL = "androidx.compose.material:material:$COMPOSE_VERSION" // Material Design
        const val UI = "androidx.compose.ui:ui:$COMPOSE_VERSION"
        const val MATERIAL_ICONS_CORE = "androidx.compose.material:material-icons-core:$COMPOSE_VERSION" // Material design icons
        const val MATERIAL_ICONS_EXTENDED = "androidx.compose.material:material-icons-extended:$COMPOSE_VERSION"
        const val NAVIGATION = "androidx.navigation:navigation-compose:$COMPOSE_NAVIGATION_VERSION"
        const val LIVEDATA = "androidx.compose.runtime:runtime-livedata:$COMPOSE_VERSION"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling:$COMPOSE_UI_TOOLING_VERSION" // Tooling support (Previews, etc.)
    }

    object Coroutines {
        private const val COROUTINES_ANDROID_VERSION = "1.4.2"

        const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_ANDROID_VERSION"
    }

    object HMS {
        const val HWID_VERSION = "5.1.0.302"
        const val OCR_VERSION = "2.0.5.300"

        const val HWID = "com.huawei.hms:hwid:$HWID_VERSION"
        const val TEXT_RECOGNITION = "com.huawei.hms:ml-computer-vision-ocr:$OCR_VERSION"
        const val TEXT_RECOGNITION_LATIN = "com.huawei.hms:ml-computer-vision-ocr-latin-model:$OCR_VERSION"
        const val TEXT_RECOGNITION_CHINESE_ENGLISH = "com.huawei.hms:ml-computer-vision-ocr-cn-model:$OCR_VERSION"
        const val TEXT_RECOGNITION_JAPANESE_KOREAN = "com.huawei.hms:ml-computer-vision-ocr-jk-model:$OCR_VERSION"
    }

    object Kotlin {
        private const val VERSION = "1.4.10"

        const val STANDARD_LIB = "org.jetbrains.kotlin:kotlin-stdlib:$VERSION"
    }

    object Hilt {
        private const val HILT_VERSION = "2.31.2-alpha"
        private const val HILT_LIFECYCLE_VIEWMODEL_VERSION = "1.0.0-alpha03"
        private const val HILT_COMPILER_VERSION = "1.0.0-alpha03"

        const val HILT_ANDROID = "com.google.dagger:hilt-android:$HILT_VERSION"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:$HILT_VERSION"
        const val HILT_LIFECYCLE_VIEWMODEL = "androidx.hilt:hilt-lifecycle-viewmodel:$HILT_LIFECYCLE_VIEWMODEL_VERSION"
        const val HILT_COMPILER = "androidx.hilt:hilt-compiler:$HILT_COMPILER_VERSION"
    }

    object Test {
        private const val JUNIT_VERSION = "4.13.1"
        private const val MOCKITO_INLINE_VERSION = "3.3.3"
        private const val MOCKITO_KOTLIN_VERSION = "2.2.0"
        private const val TRUTH_VERSION = "1.1.2"

        const val JUNIT = "junit:junit:$JUNIT_VERSION"
        const val MOCKITO_INLINE = "org.mockito:mockito-inline:$MOCKITO_INLINE_VERSION"
        const val MOCKITO_KOTLIN = "com.nhaarman.mockitokotlin2:mockito-kotlin:$MOCKITO_KOTLIN_VERSION"
        const val TRUTH = "com.google.truth:truth:$TRUTH_VERSION"

        object AndroidX {
            private const val CORE_VERSION = "1.0.0"
            private const val JUNIT_VERSION = "1.1.0"

            const val CORE = "androidx.test:core:$CORE_VERSION"
            const val JUNIT_RUNNER = "androidx.test:runner:$JUNIT_VERSION"
            const val JUNIT_RULES = "androidx.test:rules:$JUNIT_VERSION"
        }

        object UI {
            private const val COMPOSE_UI_TEST_JUNIT4_VERSION = "1.0.0-alpha10"
            const val COMPOSE_UI_TEST_JUNIT4 = "androidx.compose.ui:ui-test-junit4:$COMPOSE_UI_TEST_JUNIT4_VERSION"
        }
    }

}

// Modules
val DependencyHandler.FEATURE_SPLASH
    get() = implementation(project(Modules.Features.SPLASH))

// Layers
val DependencyHandler.LAYER_UI
    get() = implementation(project(Modules.Layers.UI))

// Dependencies
val DependencyHandler.ACCOMPANIST
    get() = implementation(Libraries.ACCOMPANIST_COIL)

val DependencyHandler.BASE
    get() = base()

val DependencyHandler.COMPOSE
    get() = compose()

val DependencyHandler.DAGGER_HILT
    get() = hilt()

val DependencyHandler.KOTLIN_STANDARD_LIBRARY
    get() = implementation(Libraries.Kotlin.STANDARD_LIB)

val DependencyHandler.MATERIAL_DESIGN
    get() = implementation(Libraries.MATERIAL_DESIGN)

val DependencyHandler.SHOWKASE
    get() = showkase()

private fun DependencyHandler.base() {
    implementation(Libraries.AndroidX.APPCOMPAT)
    implementation(Libraries.AndroidX.CORE)
    implementation(Libraries.AndroidX.CORE_KTX)
    implementation(Libraries.AndroidX.FRAGMENT_KTX)
    implementation(Libraries.AndroidX.MULTIDEX)
    implementation(Libraries.AndroidX.LIFECYCLE_EXTENSIONS)
    implementation(Libraries.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Libraries.AndroidX.LIFECYCLE_VIEWMODEL_KTX)
}

private fun DependencyHandler.compose() {
    implementation(Libraries.Compose.UI)
    implementation(Libraries.Compose.FOUNDATION)
    implementation(Libraries.Compose.MATERIAL)
    implementation(Libraries.Compose.MATERIAL_ICONS_CORE)
    implementation(Libraries.Compose.MATERIAL_ICONS_EXTENDED)
    implementation(Libraries.Compose.LIVEDATA)
    implementation(Libraries.Compose.UI_TOOLING)
}

private fun DependencyHandler.hilt() {
    implementation(Libraries.Hilt.HILT_ANDROID)
    implementation(Libraries.Hilt.HILT_LIFECYCLE_VIEWMODEL)
    kapt(Libraries.Hilt.HILT_ANDROID_COMPILER)
    kapt(Libraries.Hilt.HILT_COMPILER)
}

private fun DependencyHandler.showkase() {
    implementation(Libraries.SHOWKASE)
    kapt(Libraries.SHOWKASE_COMPILER)
}

private fun DependencyHandler.implementation(depName: Any) {
    add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}

private fun DependencyHandler.api(depName: String) {
    add("api", depName)
}