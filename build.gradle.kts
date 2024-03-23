import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.android.library") version "8.2.1" apply false
    id("androidx.navigation.safeargs") version "2.5.3" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false

}