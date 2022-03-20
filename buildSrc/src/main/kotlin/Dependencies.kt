import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

const val ANDROID_APPLICATION = "com.android.application"
const val ANDROID_LIBRARY = "com.android.library"
const val DAGGER_HILT_ANDROID_PLUGIN = "dagger.hilt.android.plugin"
const val COMMON_MODULE_PLUGIN = "common-module-plugin"

object BuildScript {
    const val VERSIONS_PLUGIN_VERSION = "0.36.0"
    const val VERSIONS_PLUGIN = "com.github.ben-manes.versions"
}

object Modules {
    const val TESTING = ":testing"
    const val TRANSLATE = ":translate"

    object Features {
        const val FAVORITES = ":features:favorites"
        const val HISTORY = ":features:history"
        const val LANGUAGE_SELECTION = ":features:language-selection"
        const val SEARCH = ":features:search"
        const val SPLASH = ":features:splash"

    }

    object Layers {
        const val DATA = ":layers:data"
        const val DOMAIN = ":layers:domain"
        const val UI = ":layers:ui"
    }
}

object Libraries {
    private const val COIL_VERSION = "1.4.0"
    private const val MATERIAL_DESIGN_VERSION = "1.5.0"
    private const val TIMBER_VERSION = "5.0.1"

    const val COIL_COMPOSE = "io.coil-kt:coil-compose:$COIL_VERSION"
    const val COIL_GIF = "io.coil-kt:coil-gif:$COIL_VERSION"
    const val MATERIAL_DESIGN = "com.google.android.material:material:$MATERIAL_DESIGN_VERSION"
    const val TIMBER = "com.jakewharton.timber:timber:$TIMBER_VERSION"

    object AndroidX {
        private const val APPCOMPAT_VERSION = "1.4.1"
        private const val CORE_VERSION = "1.7.0"
        private const val DATASTORE_VERSION = "1.0.0"
        private const val FRAGMENT_KTX_VERSION = "1.4.1"
        private const val MULTIDEX_VERSION = "2.0.1"
        private const val LIFECYCLE_VERSION = "2.2.0"
        private const val ROOM_VERSION = "2.4.1"
        private const val SPLASH_API_VERSION = "1.0.0-beta01"

        const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:1.4.0"
        const val APPCOMPAT = "androidx.appcompat:appcompat:$APPCOMPAT_VERSION"
        const val CORE = "androidx.core:core:$CORE_VERSION"
        const val CORE_KTX = "androidx.core:core-ktx:$CORE_VERSION"
        const val DATASTORE = "androidx.datastore:datastore-preferences:$DATASTORE_VERSION"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:$FRAGMENT_KTX_VERSION"
        const val MULTIDEX = "androidx.multidex:multidex:$MULTIDEX_VERSION"
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
        const val SPLASH_API = "androidx.core:core-splashscreen:$SPLASH_API_VERSION"
    }

    object Compose {
        private const val COMPOSE_NAVIGATION_VERSION = "2.4.1"
        private const val COMPOSE_UI_TOOLING_VERSION = "1.0.5"
        private const val COMPOSE_VIEWMODEL_VERSION = "2.4.1"
        private const val COMPOSE_HILT_VIEWMODEL_VERSION = "1.0.0"
        const val COMPOSE_VERSION = "1.1.0"

        const val ANIMATION = "androidx.compose.animation:animation:$COMPOSE_VERSION"
        const val COMPILER = "androidx.compose.compiler:compiler:$COMPOSE_VERSION"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout-compose:1.0.0"
        const val FOUNDATION =
            "androidx.compose.foundation:foundation:$COMPOSE_VERSION" // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
        const val FOUNDATION_LAYOUT =
            "androidx.compose.foundation:foundation-layout:$COMPOSE_VERSION"
        const val MATERIAL =
            "androidx.compose.material:material:$COMPOSE_VERSION" // Material Design
        const val MATERIAL_ICONS_CORE =
            "androidx.compose.material:material-icons-core:$COMPOSE_VERSION" // Material design icons
        const val MATERIAL_ICONS_EXTENDED =
            "androidx.compose.material:material-icons-extended:$COMPOSE_VERSION"
        const val NAVIGATION = "androidx.navigation:navigation-compose:$COMPOSE_NAVIGATION_VERSION"
        const val RUNTIME = "androidx.compose.runtime:runtime:$COMPOSE_VERSION"
        const val RUNTIME_LIVEDATA = "androidx.compose.runtime:runtime-livedata:$COMPOSE_VERSION"
        const val UI = "androidx.compose.ui:ui:$COMPOSE_VERSION"
        const val UI_TOOLING =
            "androidx.compose.ui:ui-tooling:$COMPOSE_UI_TOOLING_VERSION" // Tooling support (Previews, etc.)
        const val VIEWMODEL =
            "androidx.lifecycle:lifecycle-viewmodel-compose:$COMPOSE_VIEWMODEL_VERSION"
        const val HILT_NAVIGATION =
            "androidx.hilt:hilt-navigation-compose:${COMPOSE_HILT_VIEWMODEL_VERSION}"

        object Test {
            const val UI_TEST = "androidx.compose.ui:ui-test-junit4:$COMPOSE_VERSION"
        }
    }

    object Google {
        object MLKit {
            private const val TRANSLATE_VERSION = "17.0.0"

            const val TRANSLATE = "com.google.mlkit:translate:$TRANSLATE_VERSION"
        }
    }

    object Kotlin {
        private const val VERSION = "1.6.10"

        const val STANDARD_LIB = "org.jetbrains.kotlin:kotlin-stdlib:$VERSION"

        object Coroutines {
            private const val COROUTINES_ANDROID_VERSION = "1.6.0"

            object Test {
                const val TEST =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-test:$COROUTINES_ANDROID_VERSION"
            }
        }
    }

    object Ktor {
        private const val KTOR_VERSION = "1.6.7"
        private const val LOGBACK_VERSION = "1.2.10"

        const val KTOR_ANDROID_CLIENT = "io.ktor:ktor-client-android:$KTOR_VERSION"
        const val KTOR_CLIENT_CIO = "io.ktor:ktor-client-cio:$KTOR_VERSION"
        const val KTOR_CLIENT_LOGGING = "io.ktor:ktor-client-logging:$KTOR_VERSION"
        const val KTOR_KOTLINX_SERIALIZATION = "io.ktor:ktor-client-serialization:$KTOR_VERSION"
        const val KTOR_SERIALIZATION_JVM = "io.ktor:ktor-client-serialization-jvm:$KTOR_VERSION"

        const val LOGBACK_CLASSIC = "ch.qos.logback:logback-classic:$LOGBACK_VERSION"
    }

    object KotlinxSerialization {
        private const val KOTLINX_SERIALIZATION_JSON_VERSION = "1.3.2"

        const val KOTLINX_SERIALIZATION_JSON =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:$KOTLINX_SERIALIZATION_JSON_VERSION"
    }

    object Hilt {
        private const val HILT_VERSION = "2.41"

        const val HILT_ANDROID = "com.google.dagger:hilt-android:$HILT_VERSION"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-compiler:$HILT_VERSION"

        object Test {
            const val TESTING = "com.google.dagger:hilt-android-testing:$HILT_VERSION"
        }
    }

    object Test {
        private const val JUNIT_VERSION = "4.13.2"
        private const val MOCKK_VERSION = "1.10.6"
        private const val TRUTH_VERSION = "1.1.2"

        const val JUNIT = "junit:junit:$JUNIT_VERSION"
        const val MOCKK = "io.mockk:mockk:$MOCKK_VERSION"
        const val TRUTH = "com.google.truth:truth:$TRUTH_VERSION"

        object AndroidX {
            private const val ARCH_VERSION = "2.1.0"
            private const val CORE_VERSION = "1.0.0"
            private const val JUNIT_VERSION = "1.1.1"

            const val ARCH = "androidx.arch.core:core-testing:$ARCH_VERSION"
            const val CORE = "androidx.test:core:$CORE_VERSION"
            const val JUNIT_KTX = "androidx.test.ext:junit-ktx:$JUNIT_VERSION"
            const val JUNIT_RUNNER = "androidx.test:runner:$JUNIT_VERSION"
            const val JUNIT_RULES = "androidx.test:rules:$JUNIT_VERSION"
        }
    }

}

// Internal Libraries
val DependencyHandler.TESTING
    //get() = androidTestImplementation(project(Modules.TESTING))
    get() = implementation(project(Modules.TESTING))

val DependencyHandler.TESTING_IMPL
    get() = implementation(project(Modules.TESTING))

fun DependencyHandler.testing() {
    implementation(project(Modules.TESTING))
    androidTestImplementation(project(Modules.TESTING))
}

val DependencyHandler.TRANSLATE
    get() = api(project(Modules.TRANSLATE))

// Modules
val DependencyHandler.FEATURE_FAVORITES
    get() = api(project(Modules.Features.FAVORITES))

val DependencyHandler.FEATURE_HISTORY
    get() = api(project(Modules.Features.HISTORY))

val DependencyHandler.FEATURE_LANGUAGE_SELECTION
    get() = api(project(Modules.Features.LANGUAGE_SELECTION))

val DependencyHandler.FEATURE_SEARCH
    get() = api(project(Modules.Features.SEARCH))

val DependencyHandler.FEATURE_SPLASH
    get() = implementation(project(Modules.Features.SPLASH))

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

val DependencyHandler.COMPOSE_NAVIGATION
    get() = implementation(Libraries.Compose.NAVIGATION)

val DependencyHandler.COMPOSE_VIEWMODEL
    get() = implementation(Libraries.Compose.VIEWMODEL)

val DependencyHandler.COMPOSE_HILT_NAVIGATION
    get() = implementation(Libraries.Compose.HILT_NAVIGATION)

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

val DependencyHandler.LOTTIE_COMPOSE
    get() = implementation("com.airbnb.android:lottie-compose:5.0.3")

val DependencyHandler.MATERIAL_DESIGN
    get() = implementation(Libraries.MATERIAL_DESIGN)

val DependencyHandler.NAVIGATION_COMMON_KTX
    get() = implementation("androidx.navigation:navigation-common-ktx:2.4.1")

val DependencyHandler.ROOM
    get() = room()

val DependencyHandler.TURBINE
    get() = {
        androidTestImplementation("app.cash.turbine:turbine:0.7.0")
        testImplementation("app.cash.turbine:turbine:0.7.0")
    }

// Test
val DependencyHandler.ANDROID_TEST
    get() = androidInstrumentationTest()

val DependencyHandler.COMPOSE_UI_TEST
    get() = uiTest()

val DependencyHandler.COROUTINES_TEST
    get() = coroutinesTest()

val DependencyHandler.LOCAL_TEST
    get() = test()

val DependencyHandler.DAGGER_HILT_TEST
    get() = hiltTest()

val DependencyHandler.DAGGER_HILT_ANDROID_TEST
    get() = hiltAndroidTest()

private fun DependencyHandler.accompanist() {
    implementation(Libraries.COIL_COMPOSE)
    implementation(Libraries.COIL_GIF)
}

private fun DependencyHandler.base() {
    implementation(Libraries.AndroidX.ACTIVITY_COMPOSE)
    implementation(Libraries.AndroidX.APPCOMPAT)
    implementation(Libraries.AndroidX.CORE)
    implementation(Libraries.AndroidX.CORE_KTX)
    implementation(Libraries.AndroidX.DATASTORE)
    implementation(Libraries.AndroidX.FRAGMENT_KTX)
    implementation(Libraries.AndroidX.MULTIDEX)
    implementation(Libraries.AndroidX.LIFECYCLE_EXTENSIONS)
    implementation(Libraries.AndroidX.LIFECYCLE_EXTENSIONS_LIVEDATA)
    implementation(Libraries.AndroidX.LIFECYCLE_RUNTIME_KTX)
    implementation(Libraries.AndroidX.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Libraries.AndroidX.SPLASH_API)

    implementation(Libraries.TIMBER)
}

private fun DependencyHandler.compose() {
    implementation(Libraries.Compose.ANIMATION)
    implementation(Libraries.Compose.COMPILER)
    implementation(Libraries.Compose.CONSTRAINT_LAYOUT)
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

private fun DependencyHandler.hilt() {
    implementation(Libraries.Hilt.HILT_ANDROID)
    kapt(Libraries.Hilt.HILT_ANDROID_COMPILER)
}

private fun DependencyHandler.ktor() {
    implementation(Libraries.Ktor.LOGBACK_CLASSIC)

    api(Libraries.Ktor.KTOR_ANDROID_CLIENT)
    implementation(Libraries.Ktor.KTOR_CLIENT_CIO)
    implementation(Libraries.Ktor.KTOR_CLIENT_LOGGING)
    implementation(Libraries.Ktor.KTOR_KOTLINX_SERIALIZATION)
    implementation(Libraries.Ktor.KTOR_SERIALIZATION_JVM)
}

private fun DependencyHandler.kotlinxSerialization() {
    implementation(Libraries.KotlinxSerialization.KOTLINX_SERIALIZATION_JSON)
}

private fun DependencyHandler.test() {
    testImplementation(Libraries.Test.JUNIT)
    testImplementation(Libraries.Test.TRUTH)
    androidTestImplementation(Libraries.Test.TRUTH)
}

fun DependencyHandler.androidInstrumentationTest() {
    testImplementation(Libraries.Test.AndroidX.ARCH)
    testImplementation(Libraries.Test.AndroidX.CORE)
    testImplementation(Libraries.Test.AndroidX.JUNIT_KTX)
    testImplementation(Libraries.Test.AndroidX.JUNIT_RUNNER)
    testImplementation(Libraries.Test.AndroidX.JUNIT_RULES)
}

private fun DependencyHandler.coroutinesTest() {
    androidTestImplementation(Libraries.Kotlin.Coroutines.Test.TEST)
}

fun DependencyHandler.hiltTest() {
    testImplementation(Libraries.Hilt.Test.TESTING)
    kaptTest(Libraries.Hilt.HILT_ANDROID_COMPILER)
}

fun DependencyHandler.hiltAndroidTest() {
    androidTestImplementation(Libraries.Hilt.Test.TESTING)
    kaptAndroidTest(Libraries.Hilt.HILT_ANDROID_COMPILER)
}

private fun DependencyHandler.uiTest() {
    androidTestImplementation(Libraries.Compose.Test.UI_TEST)
}

private fun DependencyHandler.room() {
    implementation(Libraries.AndroidX.ROOM)
    implementation(Libraries.AndroidX.ROOM_KTX)
    kapt(Libraries.AndroidX.ROOM_COMPILER)
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

private fun DependencyHandler.kaptTest(depName: Any) {
    add("kaptTest", depName)
}

private fun DependencyHandler.kaptAndroidTest(depName: Any) {
    add("kaptAndroidTest", depName)
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