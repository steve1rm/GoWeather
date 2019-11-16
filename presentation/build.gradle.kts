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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

repositories {
    google()
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
    implementation(Libraries.recyclerView)
    implementation(Libraries.paracelerApi)
    kapt(Libraries.paraceler)
    implementation(Libraries.supportTransition)
    implementation(Libraries.loggingInterceptor)

    testImplementation("androidx.fragment:fragment-testing:1.1.0-alpha09")
    debugImplementation("androidx.fragment:fragment-testing:1.1.0-alpha09")

    implementation("com.google.android.gms:play-services-location:16.0.0")
    implementation(Libraries.lifecycleExtensions)

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
    implementation(TestLibraries.espressoIdlingResource)

   // implementation(TestLibraries.fragmentTesting)
   // debugImplementation(TestLibraries.fragmentTesting)

    kaptTest(Libraries.daggerCompiler)
    kaptTest(Libraries.daggerProcessor)

    androidTestImplementation(TestLibraries.espressoCore) {
        exclude(module = "idling-concurrent")
    }
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
    androidTestImplementation(TestLibraries.espressoContrib,  {
        exclude(module = "support-annotation")
    })

    debugImplementation(TestLibraries.okhttp3IdlingResource, {
        exclude(module = "support-annotation")
    })

    androidTestImplementation(TestLibraries.espressoIdlingResource)
    androidTestImplementation(TestLibraries.idlingConcurrent)
    androidTestImplementation(TestLibraries.espressoIntents)
    androidTestImplementation(Libraries.converterGson)
    implementation(Libraries.retrofit2)
    androidTestImplementation(Libraries.adapterRxjava2)

    kaptAndroidTest(Libraries.daggerCompiler)
    kaptAndroidTest(Libraries.daggerProcessor)

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation("com.google.code.findbugs:jsr305:3.0.2")

    implementation("com.jakewharton.threetenabp:threetenabp:1.2.1")
}
