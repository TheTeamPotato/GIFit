plugins {
    id(BuildScript.VERSIONS_PLUGIN) version (BuildScript.VERSIONS_PLUGIN_VERSION)
}

buildscript {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://developer.huawei.com/repo/")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.android.tools.build:gradle:7.1.1")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        maven(url = "https://developer.huawei.com/repo/")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}