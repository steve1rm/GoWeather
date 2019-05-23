plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)

    defaultConfig {
        applicationId = "me.androidbox.presentation"
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Versions.versionCode
        versionName = Versions.versionName

       // testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "me.androidbox.presentation.di.GoWeatherTestRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    testOptions.unitTests.isIncludeAndroidResources = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(Libraries.kotlinStdlib)
    implementation(Libraries.appCompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.daggerAndroid)
    implementation(Libraries.daggerSupport)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerProcessor)
    implementation(Libraries.rxjava)
    implementation(Libraries.rxAndroid)
    implementation(Libraries.rxkotlin)
    implementation(Libraries.okhttp)
    implementation(Libraries.loggingInterceptor)
    implementation(Libraries.retrofit2)
    implementation(Libraries.adapterRxjava2)
    implementation(Libraries.converterGson)
    implementation(Libraries.recyclerView)
    implementation(Libraries.paracelerApi)
    kapt(Libraries.paraceler)
    implementation(Libraries.supportTransition)

    implementation("com.google.android.gms:play-services-location:16.0.0")

    testImplementation(TestLibraries.assertJ)
    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.runner)
    testImplementation(TestLibraries.robolectric)
    testImplementation(TestLibraries.androidxJunit)
    testImplementation(TestLibraries.androidxTruth)
    implementation(TestLibraries.androidxCore)
    testImplementation(TestLibraries.mockitoKotlin)
    testImplementation(Libraries.daggerAndroid)
    testImplementation(Libraries.daggerSupport)
    testImplementation(Libraries.kotlinStdlib)

    kaptTest(Libraries.daggerCompiler)
    kaptTest(Libraries.daggerProcessor)

    androidTestImplementation(TestLibraries.espressoCore)
    androidTestImplementation(TestLibraries.androidxTruth)
    androidTestImplementation(TestLibraries.androidxJunit)
    androidTestImplementation(TestLibraries.runner)
    androidTestImplementation(TestLibraries.androidxRules)
    androidTestImplementation(TestLibraries.mockWebServer, {
        exclude("com.squareup.okhttp3", "okhttp")
    })

    androidTestImplementation(Libraries.daggerAndroid)
    androidTestImplementation(Libraries.daggerSupport)
    androidTestImplementation(TestLibraries.mockitoKotlin)
    androidTestImplementation(TestLibraries.mockitoAndroid)
    androidTestImplementation(TestLibraries.espressoContrib)

    kaptAndroidTest(Libraries.daggerCompiler)
    kaptAndroidTest(Libraries.daggerProcessor)

    implementation(project(":domain"))
    implementation(project(":data"))
}
