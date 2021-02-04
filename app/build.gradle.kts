plugins {
    id(ANDROID_APPLICATION)
    id(DAGGER_HILT_ANDROID_PLUGIN)
    id(COMMON_MODULE_PLUGIN)
}

android.buildFeatures.compose = true

dependencies {
    FEATURE_SPLASH
    LAYER_UI

    BASE
    COMPOSE
    DAGGER_HILT
    KOTLIN_STANDARD_LIBRARY
}