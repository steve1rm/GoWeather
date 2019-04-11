plugins {
    id("com.android.application")
    id("kotlin-android-extensions")
    kotlin("android")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)

    defaultConfig {
        applicationId = "me.androidbox.presentation"
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(Libraries.appCompat)
    implementation(Libraries.constraintLayout)

    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.espressoCore)
    testImplementation(TestLibraries.runner)

    implementation(project(":domain"))
}
