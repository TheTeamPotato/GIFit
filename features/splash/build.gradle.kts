plugins {
    id(ANDROID_LIBRARY)
    id(DAGGER_HILT_ANDROID_PLUGIN)
    id(COMMON_MODULE_PLUGIN)
}

android.buildFeatures.compose = true

dependencies {
    LAYER_UI

    ACCOMPANIST
    BASE
    COMPOSE
    DAGGER_HILT
    KOTLIN_STANDARD_LIBRARY
    MATERIAL_DESIGN

    ANDROID_TEST
    COMPOSE_UI_TEST
    COROUTINES_TEST
    LOCAL_TEST_JUNIT5
    TESTING
}

kapt {
    correctErrorTypes = true
}

moduleConfigurations {
    useJUnitRunner5 = true
}