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
    implementation(Libraries.daggerAndroid)
    implementation(Libraries.daggerSupport)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerProcessor)
    implementation(Libraries.rxjava)
    implementation(Libraries.rxAndroid)
    implementation(Libraries.rxkotlin)

    testImplementation(TestLibraries.assertJ)
    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.espressoCore)
    testImplementation(TestLibraries.runner)

    implementation(project(":domain"))
    implementation(project(":data"))
   // implementation(project(":dicore"))
}
