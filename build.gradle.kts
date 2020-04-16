import dev.arunkumar.scabbard.gradle.ScabbardSpec

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
        google()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradleVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
        classpath("com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseCrashlyticsGradleVersion}")
        classpath("com.google.gms:google-services:4.3.3")
        classpath("com.facebook.testing.screenshot:plugin:0.11.0")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

plugins {
    id("com.gradle.build-scan") version "2.1"
    id("scabbard.gradle") version "0.1.0"
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"

  //  publishAlways()
}


scabbard.configure(closureOf<ScabbardSpec> {
    enabled(true)
})
