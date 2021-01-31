plugins {
    id(ANDROID_APPLICATION)
    id(DAGGER_HILT_ANDROID_PLUGIN)
    id(COMMON_MODULE_PLUGIN)
}

android.buildFeatures.compose = true

dependencies {
    ACCOMPANIST
    BASE
    COMPOSE
    DAGGER_HILT
    KOTLIN_STANDARD_LIBRARY
    MATERIAL_DESIGN
    SHOWKASE
}