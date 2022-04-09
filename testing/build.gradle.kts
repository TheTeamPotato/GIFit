plugins {
    id(ANDROID_LIBRARY)
    id(COMMON_MODULE_PLUGIN)
}

dependencies {
    BASE
    KOTLIN_STANDARD_LIBRARY

    //implementation(Libraries.Kotlin.Coroutines.Test.TEST)

    implementation(Libraries.Hilt.Test.TESTING)
    kapt(Libraries.Hilt.HILT_ANDROID_COMPILER)

    implementation(Libraries.Test.AndroidX.ARCH)
    implementation(Libraries.Test.AndroidX.CORE)
    implementation(Libraries.Test.AndroidX.JUNIT_KTX)
    implementation(Libraries.Test.AndroidX.JUNIT_RUNNER)
    implementation(Libraries.Test.AndroidX.JUNIT_RULES)

    implementation(Libraries.Test.JUNIT)
    implementation(Libraries.Test.TRUTH)
}

kapt {
    correctErrorTypes = true
}
