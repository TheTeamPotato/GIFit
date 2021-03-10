plugins {
    id(ANDROID_LIBRARY)
    id(DAGGER_HILT_ANDROID_PLUGIN)
    id(COMMON_MODULE_PLUGIN)
}

android.packagingOptions {
    resources.excludes.apply {
        add("META-INF/AL2.0")
        add("META-INF/LGPL2.1")
    }
}

dependencies {
    BASE
    DAGGER_HILT
    KOTLIN_STANDARD_LIBRARY
    KTOR
    KOTLINX_SERIALIZATION
    ROOM

    ANDROID_TEST
    LOCAL_TEST
    TESTING
}

kapt {
    correctErrorTypes = true
}

moduleConfigurations {
    useJUnitRunner5 = true
}