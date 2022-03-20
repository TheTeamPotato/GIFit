plugins {
    id(ANDROID_LIBRARY)
    id(COMMON_MODULE_PLUGIN)
}

android {
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = Libraries.Compose.COMPOSE_VERSION
    }
}

dependencies {
    ACCOMPANIST
    BASE
    COMPOSE
    KOTLIN_STANDARD_LIBRARY
    LOTTIE_COMPOSE
    MATERIAL_DESIGN
    NAVIGATION_COMMON_KTX

    ANDROID_TEST
    COROUTINES_TEST
}