plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("android")
    kotlin("kapt")
}

android {
   compileSdkVersion(Versions.compileSdkVersion)
}

dependencies {
    implementation(Libraries.kotlinStdlib)
    implementation(Libraries.appCompat)
    implementation(Libraries.gson)
    implementation(Libraries.retrofit2)
    implementation(Libraries.adapterRxjava2)
    implementation(Libraries.daggerAndroid)
    implementation(Libraries.daggerSupport)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerProcessor)
    implementation(Libraries.rxjava)
    implementation(Libraries.rxkotlin)
    implementation(Libraries.adapterRxjava2)
    implementation(Libraries.okhttp)
    implementation(Libraries.loggingInterceptor)

    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.assertJ)
    testImplementation(TestLibraries.mockitoKotlin)
    testImplementation(Libraries.daggerAndroid)
    testImplementation(Libraries.daggerSupport)
    testImplementation(TestLibraries.mockWebServer)

    kaptTest(Libraries.daggerCompiler)
    kaptTest(Libraries.daggerProcessor)

    implementation(project(":dicore"))
}
