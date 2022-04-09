plugins {
    id(ANDROID_LIBRARY)
    id(COMMON_MODULE_PLUGIN)
}

dependencies {
    BASE
    GOOGLE_MLKIT_TRANSLATE
    KOTLIN_STANDARD_LIBRARY

    ANDROID_TEST
    COROUTINES_TEST
}

kapt {
    correctErrorTypes = true
}