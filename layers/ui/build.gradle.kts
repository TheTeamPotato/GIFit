plugins {
    id(ANDROID_LIBRARY)
    id(COMMON_MODULE_PLUGIN)
}

android.buildFeatures.compose = true

dependencies {
    ACCOMPANIST
    BASE
    COMPOSE
    KOTLIN_STANDARD_LIBRARY
    MATERIAL_DESIGN
    SHOWKASE

    ANDROID_TEST
    COROUTINES_TEST
    LOCAL_TEST_JUNIT5
}