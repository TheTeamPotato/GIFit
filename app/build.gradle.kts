plugins {
    id(ANDROID_APPLICATION)
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
    FEATURE_FAVORITES
    FEATURE_HISTORY
    FEATURE_LANGUAGE_SELECTION
    FEATURE_SEARCH
    FEATURE_SPLASH
    LAYER_UI

    BASE
    COMPOSE
    COMPOSE_NAVIGATION
    COMPOSE_VIEWMODEL
    DAGGER_HILT
    KOTLIN_STANDARD_LIBRARY

    ANDROID_TEST
    COROUTINES_TEST
}

kapt {
    correctErrorTypes = true
}