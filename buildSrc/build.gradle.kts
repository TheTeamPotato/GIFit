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
    //compileOnly(gradleApi())
    //implementation(kotlin("gradle-plugin"))
    implementation("com.android.tools.build:gradle:7.1.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.40.5")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:10.2.1")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.4.31")
    implementation("io.ktor:ktor-serialization:1.5.1")
    implementation("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.0")
}