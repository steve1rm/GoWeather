plugins {
    id("kotlin")
    kotlin("kapt")
}

dependencies {
    implementation(Libraries.kotlinStdlib)
    implementation(Libraries.rxjava)
    implementation(Libraries.rxkotlin)
    implementation(Libraries.daggerAndroid)
    implementation(Libraries.daggerSupport)
    kapt(Libraries.daggerCompiler)
    kapt(Libraries.daggerProcessor)

    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.assertJ)
    testImplementation(TestLibraries.mockitoKotlin)
    testImplementation(Libraries.daggerAndroid)
    testImplementation(Libraries.daggerSupport)
    kaptTest(Libraries.daggerCompiler)
    kaptTest(Libraries.daggerProcessor)
}

