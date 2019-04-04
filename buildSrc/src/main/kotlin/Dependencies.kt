object Plugins {
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val androidGralePlugin = "com.android.tools.build:gradle:${Versions.gradleVersion}"
}

object Libraries {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.androidXVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val converterSimpleXml = "com.squareup.retrofit2:converter-simplexml:${Versions.retrofitVersion}"
    const val adapterRxjava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.daggerAndroidVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerAndroidVersion}"
    const val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.daggerAndroidVersion}"
    const val daggerSupport = "com.google.dagger:dagger-android-support:${Versions.daggerAndroidVersion}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjavaVersion}"
    const val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlinVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
}

object TestLibraries {
    const val junit = "junit:junit:${Versions.jUnitVersion}"
    const val assertJ = "org.assertj:assertj-core:${Versions.assertJVersion}"
    const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlinVersion}"
    const val runner = "androidx.test:runner:${Versions.runnerVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
}
