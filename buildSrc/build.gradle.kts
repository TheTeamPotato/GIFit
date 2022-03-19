plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("common-module-plugin") {
            id = "common-module-plugin"
            implementationClass = "CommonModulePlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

dependencies {
    implementation("com.android.tools.build:gradle:7.1.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.41")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.6.10")
    implementation("io.ktor:ktor-serialization:1.5.1")
}