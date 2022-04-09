plugins {
    id(ANDROID_LIBRARY)
    id(DAGGER_HILT_ANDROID_PLUGIN)
    id(COMMON_MODULE_PLUGIN)
}

android {
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = Libraries.Compose.COMPOSE_VERSION
    }
}

dependencies {
    LAYER_DOMAIN
    LAYER_UI

    ACCOMPANIST
    BASE
    COMPOSE
    COMPOSE_VIEWMODEL
    DAGGER_HILT
    KOTLIN_STANDARD_LIBRARY
    MATERIAL_DESIGN

    ANDROID_TEST
    COMPOSE_UI_TEST
    COROUTINES_TEST
    TESTING
}

kapt {
    correctErrorTypes = true
}