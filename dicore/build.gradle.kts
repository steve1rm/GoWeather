plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
}

dependencies {
    implementation(Libraries.daggerAndroid)
    implementation(Libraries.daggerSupport)
    implementation(Libraries.kotlinStdlib)
    implementation(Libraries.okhttp)
    implementation(Libraries.loggingInterceptor)
    implementation(Libraries.retrofit2)
    implementation(Libraries.adapterRxjava2)
    implementation(Libraries.converterGson)

    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerProcessor)

    implementation(project(":data"))
}
