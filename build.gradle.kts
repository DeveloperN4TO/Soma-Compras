// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}

buildscript {
    dependencies {
       repositories{
           mavenCentral()
           google()
           maven("https://jitpack.io")
       }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}