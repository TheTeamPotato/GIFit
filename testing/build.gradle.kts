plugins {
    id(ANDROID_LIBRARY)
    id(COMMON_MODULE_PLUGIN)
}

dependencies {
    BASE
    KOTLIN_STANDARD_LIBRARY

    implementation(Libraries.Kotlin.Coroutines.Test.TEST)

    implementation(Libraries.Hilt.Test.TESTING)
    kapt(Libraries.Hilt.HILT_ANDROID_COMPILER)

    implementation(Libraries.Test.AndroidX.ARCH)
    implementation(Libraries.Test.AndroidX.CORE)
    implementation(Libraries.Test.AndroidX.JUNIT_KTX)
    implementation(Libraries.Test.AndroidX.JUNIT_RUNNER)
    implementation(Libraries.Test.AndroidX.JUNIT_RULES)

    implementation(Libraries.Test.JUNIT)
    //implementation(Libraries.Test.MOCKK)
    implementation(Libraries.Test.TRUTH)

    /*implementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.2")
    implementation("org.junit.jupiter:junit-jupiter-params:5.3.2")
    runtimeOnly("org.junit.vintage:junit-vintage-engine:5.3.2")*/
}

kapt {
    correctErrorTypes = true
}

/*
moduleConfigurations {
    useJUnitRunner5 = true
}*/
