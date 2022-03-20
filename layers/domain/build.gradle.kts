plugins {
    id(ANDROID_LIBRARY)
    id(DAGGER_HILT_ANDROID_PLUGIN)
    id(COMMON_MODULE_PLUGIN)
}

dependencies {
    LAYER_DATA
    TRANSLATE

    BASE
    DAGGER_HILT
    KOTLIN_STANDARD_LIBRARY

    ANDROID_TEST
    LOCAL_TEST
}

kapt {
    correctErrorTypes = true
}