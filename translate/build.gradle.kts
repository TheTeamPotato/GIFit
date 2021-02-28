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
    LOCAL_TEST_JUNIT5
}

kapt {
    correctErrorTypes = true
}

moduleConfigurations {
    useJUnitRunner5 = true
}