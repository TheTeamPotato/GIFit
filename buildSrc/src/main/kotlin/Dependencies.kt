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
    private const val KOTLIN_VERSION = "1.4.31"

    const val KTLINT_PLUGIN_VERSION = "9.4.1"
    const val VERSIONS_PLUGIN_VERSION = "0.36.0"
    const val JUNIT5_PLUGIN = "de.mannodermaus.android-junit5"
    const val KTLINT_PLUGIN = "org.jlleitschuh.gradle.ktlint"
    const val VERSIONS_PLUGIN = "com.github.ben-manes.versions"

    const val ANDROID_GRADLE_PLUGIN = "com.android.tools.build:gradle:$ANDROID_GRADLE_VERSION"
    const val ANDROID_HILT_GRADLE_PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:$ANDROID_HILT_GRADLE_VERSION"
    const val APP_GALLERY_CONNECT_PLUGIN = "com.huawei.agconnect:agcp:$APP_GALLERY_CONNECT_VERSION"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
}

object Modules {
    const val TRANSLATE = ":translate"

    object Features {
        const val SPLASH = ":features:splash"
        const val HOME = ":features:home"
    }

    object Layers {
        const val DATA = ":layers:data"
        const val DOMAIN = ":layers:domain"
        const val UI = ":layers:ui"
    }
}

object Libraries {
    private const val ACCOMPANIST_VERSION = "0.6.1"
    private const val MATERIAL_DESIGN_VERSION = "1.3.0-rc01"
    private const val PERMISSION_DISPATCHER_VERSION = "4.8.0"
    private const val SHOWKASE_VERSION = "1.0.0-alpha03"
    private const val TIMBER_VERSION = "4.7.1"

    const val ACCOMPANIST_COIL = "dev.chrisbanes.accompanist:accompanist-coil:$ACCOMPANIST_VERSION"
    const val COIL_GIF = "io.coil-kt:coil-gif:1.1.1"
    const val MATERIAL_DESIGN = "com.google.android.material:material:$MATERIAL_DESIGN_VERSION"
    const val PERMISSION_DISPATCHER =
        "org.permissionsdispatcher:permissionsdispatcher:$PERMISSION_DISPATCHER_VERSION"
    const val PERMISSION_DISPATCHER_PROCESSOR =
        "org.permissionsdispatcher:permissionsdispatcher-processor:$PERMISSION_DISPATCHER_VERSION"
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

        const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:1.3.0-alpha03"
        const val APPCOMPAT = "androidx.appcompat:appcompat:$APPCOMPAT_VERSION"
        const val CORE = "androidx.core:core:$CORE_VERSION"
        const val CORE_KTX = "androidx.core:core-ktx:$CORE_VERSION"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:$FRAGMENT_KTX_VERSION"
        const val MULTIDEX = "androidx.multidex:multidex:$MULTIDEX_VERSION"
        const val NAVIGATION_COMPONENT =
            "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_COMPONENT_VERSION"
        const val NAVIGATION_COMPONENT_UI =
            "androidx.navigation:navigation-ui-ktx:$NAVIGATION_COMPONENT_VERSION"
        const val LIFECYCLE_EXTENSIONS =
            "androidx.lifecycle:lifecycle-extensions:$LIFECYCLE_VERSION"
        const val LIFECYCLE_EXTENSIONS_LIVEDATA =
            "androidx.lifecycle:lifecycle-livedata-ktx:$LIFECYCLE_VERSION"
        const val LIFECYCLE_RUNTIME_KTX =
            "androidx.lifecycle:lifecycle-runtime-ktx:$LIFECYCLE_VERSION"
        const val LIFECYCLE_VIEWMODEL_KTX =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION"
        const val ROOM = "androidx.room:room-runtime:$ROOM_VERSION"
        const val ROOM_KTX = "androidx.room:room-ktx:$ROOM_VERSION"
        const val ROOM_COMPILER = "androidx.room:room-compiler:$ROOM_VERSION"
    }

    object Compose {
        private const val COMPOSE_NAVIGATION_VERSION = "1.0.0-alpha03"
        private const val COMPOSE_UI_TOOLING_VERSION = "1.0.0-beta01"
        private const val COMPOSE_VERSION = "1.0.0-beta01"

        const val ANIMATION = "androidx.compose.animation:animation:$COMPOSE_VERSION"
        const val FOUNDATION = "androidx.compose.foundation:foundation:$COMPOSE_VERSION" // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
        const val FOUNDATION_LAYOUT = "androidx.compose.foundation:foundation-layout:$COMPOSE_VERSION"
        const val MATERIAL = "androidx.compose.material:material:$COMPOSE_VERSION" // Material Design
        const val MATERIAL_ICONS_CORE = "androidx.compose.material:material-icons-core:$COMPOSE_VERSION" // Material design icons
        const val MATERIAL_ICONS_EXTENDED = "androidx.compose.material:material-icons-extended:$COMPOSE_VERSION"
        const val NAVIGATION = "androidx.navigation:navigation-compose:$COMPOSE_NAVIGATION_VERSION"
        const val RUNTIME = "androidx.compose.runtime:runtime:$COMPOSE_VERSION"
        const val RUNTIME_LIVEDATA = "androidx.compose.runtime:runtime-livedata:$COMPOSE_VERSION"
        const val UI = "androidx.compose.ui:ui:$COMPOSE_VERSION"
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling:$COMPOSE_UI_TOOLING_VERSION" // Tooling support (Previews, etc.)

        //const val layout = "androidx.compose.foundation:foundation-layout:$version"
        //const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
    }

    object Google {
        object MLKit {
            private const val TRANSLATE_VERSION = "16.1.1"

            const val TRANSLATE = "com.google.mlkit:translate:$TRANSLATE_VERSION"
        }
    }

    object HMS {
        const val HWID_VERSION = "5.1.0.302"
        const val OCR_VERSION = "2.0.5.300"

        const val HWID = "com.huawei.hms:hwid:$HWID_VERSION"
        const val TEXT_RECOGNITION = "com.huawei.hms:ml-computer-vision-ocr:$OCR_VERSION"
        const val TEXT_RECOGNITION_LATIN =
            "com.huawei.hms:ml-computer-vision-ocr-latin-model:$OCR_VERSION"
        const val TEXT_RECOGNITION_CHINESE_ENGLISH =
            "com.huawei.hms:ml-computer-vision-ocr-cn-model:$OCR_VERSION"
        const val TEXT_RECOGNITION_JAPANESE_KOREAN =
            "com.huawei.hms:ml-computer-vision-ocr-jk-model:$OCR_VERSION"
    }

    object Kotlin {
        private const val VERSION = "1.4.31"

        const val STANDARD_LIB = "org.jetbrains.kotlin:kotlin-stdlib:$VERSION"

        object Coroutines {
            private const val COROUTINES_ANDROID_VERSION = "1.4.2"

            const val COROUTINES_ANDROID =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_ANDROID_VERSION"

            object Test {
                const val TEST =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-test:$COROUTINES_ANDROID_VERSION"
            }
        }
    }

    object Ktor {
        private const val KTOR_VERSION = "1.5.1"

        const val KTOR_ANDROID_CLIENT = "io.ktor:ktor-client-android:$KTOR_VERSION"
        const val KTOR_CIO_CLIENT = "io.ktor:ktor-client-cio:$KTOR_VERSION"
        const val KTOR_KOTLINX_SERIALIZATION = "io.ktor:ktor-client-serialization:$KTOR_VERSION"
        const val KTOR_SERIALIZATION_JVM = "io.ktor:ktor-client-serialization-jvm:$KTOR_VERSION"
    }

    object KotlinxSerialization {
        private const val KOTLINX_SERIALIZATION_JSON_VERSION = "1.0.1"

        const val KOTLINX_SERIALIZATION_JSON =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:$KOTLINX_SERIALIZATION_JSON_VERSION"
    }

    object Hilt {
        private const val HILT_VERSION = "2.31.2-alpha"
        private const val HILT_LIFECYCLE_VIEWMODEL_VERSION = "1.0.0-alpha03"
        private const val HILT_COMPILER_VERSION = "1.0.0-alpha03"

        const val HILT_ANDROID = "com.google.dagger:hilt-android:$HILT_VERSION"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:$HILT_VERSION"
        const val HILT_LIFECYCLE_VIEWMODEL =
            "androidx.hilt:hilt-lifecycle-viewmodel:$HILT_LIFECYCLE_VIEWMODEL_VERSION"
        const val HILT_COMPILER = "androidx.hilt:hilt-compiler:$HILT_COMPILER_VERSION"

        object Test {
            const val TESTING = "com.google.dagger:hilt-android-testing:$HILT_VERSION"
        }
    }

    object Test {
        private const val HAMCREST_VERSION = "1.3"
        private const val JUNIT_VERSION = "4.13.1"
        private const val KOTLINX_COROUTINES_VERSION = "1.4.2"
        private const val MOCKITO_CORE_VERSION = "2.21.0"
        private const val MOCKITO_INLINE_VERSION = "3.3.3"
        private const val MOCKITO_KOTLIN_VERSION = "2.2.0"
        private const val MOCKK_VERSION = "1.10.6"
        private const val TRUTH_VERSION = "1.1.2"

        const val JUNIT = "junit:junit:$JUNIT_VERSION"
        const val MOCKK = "io.mockk:mockk:$MOCKK_VERSION"
        const val MOCKK_ANDROID = "io.mockk:mockk-android:$MOCKK_VERSION"
        const val MOCKITO_INLINE = "org.mockito:mockito-inline:$MOCKITO_INLINE_VERSION"
        const val MOCKITO_KOTLIN =
            "com.nhaarman.mockitokotlin2:mockito-kotlin:$MOCKITO_KOTLIN_VERSION"
        const val TRUTH = "com.google.truth:truth:$TRUTH_VERSION"
        const val MOCKITO_CORE = "org.mockito:mockito-core:$MOCKITO_CORE_VERSION"
        const val KOTLINX_COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$KOTLINX_COROUTINES_VERSION"
        const val HAMCREST = "org.hamcrest:hamcrest-all:$HAMCREST_VERSION"

        object AndroidX {
            private const val ARCH_VERSION = "2.1.0"
            private const val CORE_VERSION = "1.0.0"
            private const val JUNIT_VERSION = "1.1.1"

            const val ARCH = "androidx.arch.core:core-testing:$ARCH_VERSION"
            const val CORE = "androidx.test:core:$CORE_VERSION"
            const val JUNIT_RUNNER = "androidx.test:runner:$JUNIT_VERSION"
            const val JUNIT_RULES = "androidx.test:rules:$JUNIT_VERSION"
        }

        object UI {
            private const val COMPOSE_UI_TEST_JUNIT4_VERSION = "1.0.0-alpha10"
            const val COMPOSE_UI_TEST_JUNIT4 =
                "androidx.compose.ui:ui-test-junit4:$COMPOSE_UI_TEST_JUNIT4_VERSION"
        }
    }

}

// Internal Libraries
val DependencyHandler.TRANSLATE
    get() = api(project(Modules.TRANSLATE))

// Modules
val DependencyHandler.FEATURE_SPLASH
    get() = implementation(project(Modules.Features.SPLASH))

val DependencyHandler.FEATURE_HOME
    get() = api(project(Modules.Features.HOME))

// Layers
val DependencyHandler.LAYER_DATA
    get() = api(project(Modules.Layers.DATA))

val DependencyHandler.LAYER_DOMAIN
    get() = api(project(Modules.Layers.DOMAIN))

val DependencyHandler.LAYER_UI
    get() = implementation(project(Modules.Layers.UI))

// Dependencies
val DependencyHandler.ACCOMPANIST
    get() = accompanist()

val DependencyHandler.BASE
    get() = base()

val DependencyHandler.COMPOSE
    get() = compose()

val DependencyHandler.COROUTINES
    get() = coroutines()

val DependencyHandler.DAGGER_HILT
    get() = hilt()

val DependencyHandler.GOOGLE_MLKIT_TRANSLATE
    get() = implementation(Libraries.Google.MLKit.TRANSLATE)

val DependencyHandler.KOTLIN_STANDARD_LIBRARY
    get() = implementation(Libraries.Kotlin.STANDARD_LIB)

val DependencyHandler.KTOR
    get() = ktor()

val DependencyHandler.KOTLINX_SERIALIZATION
    get() = kotlinxSerialization()

val DependencyHandler.MATERIAL_DESIGN
    get() = implementation(Libraries.MATERIAL_DESIGN)

val DependencyHandler.ROOM
    get() = room()

val DependencyHandler.SHOWKASE
    get() = showkase()

// Test
val DependencyHandler.ANDROID_TEST
    get() = androidInstrumentationTest(useJUnit5 = true)

val DependencyHandler.COROUTINES_TEST
    get() = coroutinesTest()

val DependencyHandler.LOCAL_TEST
    get() = test(useJUnit5 = false)

val DependencyHandler.LOCAL_TEST_JUNIT5
    get() = test(useJUnit5 = true)

val DependencyHandler.HILT_TEST
    get() = hiltTest()

private fun DependencyHandler.accompanist() {
    implementation(Libraries.ACCOMPANIST_COIL)
    implementation(Libraries.COIL_GIF)
}

private fun DependencyHandler.base() {
    implementation(Libraries.AndroidX.ACTIVITY_COMPOSE)
    implementation(Libraries.AndroidX.APPCOMPAT)
    implementation(Libraries.AndroidX.CORE)
    implementation(Libraries.AndroidX.CORE_KTX)
    implementation(Libraries.AndroidX.FRAGMENT_KTX)
    implementation(Libraries.AndroidX.MULTIDEX)
    implementation(Libraries.AndroidX.LIFECYCLE_EXTENSIONS)
    implementation(Libraries.AndroidX.LIFECYCLE_EXTENSIONS_LIVEDATA)
    implementation(Libraries.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Libraries.AndroidX.LIFECYCLE_VIEWMODEL_KTX)

    implementation(Libraries.TIMBER)
}

private fun DependencyHandler.compose() {
    implementation(Libraries.Compose.ANIMATION)
    implementation(Libraries.Compose.FOUNDATION)
    implementation(Libraries.Compose.FOUNDATION_LAYOUT)
    implementation(Libraries.Compose.MATERIAL)
    implementation(Libraries.Compose.MATERIAL_ICONS_CORE)
    implementation(Libraries.Compose.MATERIAL_ICONS_EXTENDED)
    implementation(Libraries.Compose.RUNTIME)
    implementation(Libraries.Compose.RUNTIME_LIVEDATA)
    implementation(Libraries.Compose.UI)
    implementation(Libraries.Compose.UI_TOOLING)
}

private fun DependencyHandler.coroutines() {
    implementation(Libraries.Kotlin.Coroutines.COROUTINES_ANDROID)
}

private fun DependencyHandler.hilt() {
    implementation(Libraries.Hilt.HILT_ANDROID)
    implementation(Libraries.Hilt.HILT_LIFECYCLE_VIEWMODEL)
    kapt(Libraries.Hilt.HILT_ANDROID_COMPILER)
    kapt(Libraries.Hilt.HILT_COMPILER)
}

private fun DependencyHandler.ktor() {
    api(Libraries.Ktor.KTOR_ANDROID_CLIENT)
    implementation(Libraries.Ktor.KTOR_CIO_CLIENT)
    //implementation(Libraries.Ktor.KTOR_KOTLINX_SERIALIZATION)
    implementation(Libraries.Ktor.KTOR_SERIALIZATION_JVM)

    //implementation(Libraries.Ktor.KTOR_SERIALIZATION)
}

private fun DependencyHandler.kotlinxSerialization() {
    implementation(Libraries.KotlinxSerialization.KOTLINX_SERIALIZATION_JSON)
}

private fun DependencyHandler.test(useJUnit5: Boolean) {
    if (useJUnit5) {
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.2")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.3.2")
        testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.3.2")
    } else {
        testImplementation(Libraries.Test.JUNIT)
    }

    testImplementation(Libraries.Test.MOCKK)
    testImplementation(Libraries.Test.TRUTH)
}

private fun DependencyHandler.androidInstrumentationTest(useJUnit5: Boolean) {
    if (useJUnit5) {
        androidTestImplementation("org.junit.jupiter:junit-jupiter-api:5.4.1")
        androidTestImplementation("de.mannodermaus.junit5:android-test-core:1.0.0")
        androidTestRuntimeOnly("de.mannodermaus.junit5:android-test-runner:1.0.0")
    }

    androidTestImplementation(Libraries.Test.AndroidX.CORE)
    androidTestImplementation(Libraries.Test.AndroidX.JUNIT_RUNNER)
    androidTestImplementation(Libraries.Test.AndroidX.JUNIT_RULES)
    androidTestImplementation(Libraries.Test.MOCKK_ANDROID)
    androidTestImplementation(Libraries.Test.TRUTH)
}

private fun DependencyHandler.coroutinesTest() {
    androidTestImplementation(Libraries.Kotlin.Coroutines.Test.TEST)
}

private fun DependencyHandler.hiltTest() {
    androidTestImplementation(Libraries.Hilt.Test.TESTING)
    kaptAndroidTest(Libraries.Hilt.HILT_ANDROID_COMPILER)
}

private fun DependencyHandler.room() {
    implementation(Libraries.AndroidX.ROOM)
    implementation(Libraries.AndroidX.ROOM_KTX)
    kapt(Libraries.AndroidX.ROOM_COMPILER)
}

private fun DependencyHandler.showkase() {
    implementation(Libraries.SHOWKASE)
    kapt(Libraries.SHOWKASE_COMPILER)
}

private fun DependencyHandler.implementation(depName: Any) {
    add("implementation", depName)
}

private fun DependencyHandler.testImplementation(depName: Any) {
    add("testImplementation", depName)
}

private fun DependencyHandler.androidTestImplementation(depName: Any) {
    add("androidTestImplementation", depName)
}

private fun DependencyHandler.androidTestRuntimeOnly(depName: Any) {
    add("androidTestRuntimeOnly", depName)
}

private fun DependencyHandler.testRuntimeOnly(depName: Any) {
    add("testRuntimeOnly", depName)
}

private fun DependencyHandler.kaptAndroidTest(depName: Any) {
    add("kaptAndroidTest ", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}

private fun DependencyHandler.api(depName: Any) {
    add("api", depName)
}