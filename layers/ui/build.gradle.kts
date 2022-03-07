plugins {
    id(ANDROID_LIBRARY)
    id(COMMON_MODULE_PLUGIN)
}

android {
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = Libraries.Compose.COMPOSE_VERSION
    }
}

dependencies {
    implementation("androidx.navigation:navigation-common-ktx:2.4.1")
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