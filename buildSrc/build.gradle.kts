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
    jcenter()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

dependencies {
    //compileOnly(gradleApi())
    //implementation(kotlin("gradle-plugin"))
    implementation("com.android.tools.build:gradle:7.0.0-alpha08")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.31.2-alpha")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:9.4.1")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.4.30")
    implementation("io.ktor:ktor-serialization:1.5.1")
    implementation("de.mannodermaus.gradle.plugins:android-junit5:1.7.0.0")
}