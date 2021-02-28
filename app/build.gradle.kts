plugins {
    id(ANDROID_APPLICATION)
    id(DAGGER_HILT_ANDROID_PLUGIN)
    id(COMMON_MODULE_PLUGIN)
}

android.buildFeatures.compose = true

dependencies {
    TRANSLATE
    FEATURE_SPLASH
    LAYER_DATA
    LAYER_UI

    BASE
    COMPOSE
    DAGGER_HILT
    KOTLIN_STANDARD_LIBRARY

    ANDROID_TEST
    COROUTINES_TEST
    LOCAL_TEST_JUNIT5
}

kapt {
    correctErrorTypes = true
}

moduleConfigurations {
    useJUnitRunner5 = true
}