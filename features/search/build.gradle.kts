plugins {
    id(ANDROID_LIBRARY)
    id(DAGGER_HILT_ANDROID_PLUGIN)
    id(COMMON_MODULE_PLUGIN)
}

android {
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = Libraries.Compose.COMPOSE_VERSION
    }

    defaultConfig {
        testInstrumentationRunner = "com.theteampotato.gifit.testing.HiltTestRunner"
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

dependencies {
    LAYER_DOMAIN
    LAYER_UI

    BASE
    COMPOSE
    COMPOSE_NAVIGATION
    COMPOSE_HILT_NAVIGATION
    COMPOSE_VIEWMODEL
    DAGGER_HILT
    KOTLIN_STANDARD_LIBRARY
    MATERIAL_DESIGN

    ANDROID_TEST
    COMPOSE_UI_TEST
    COROUTINES_TEST
    DAGGER_HILT_TEST
    DAGGER_HILT_ANDROID_TEST
    LOCAL_TEST
    TESTING

    TURBINE
}

kapt {
    correctErrorTypes = true
}

moduleConfigurations {
    useRoboelectric = true
}
