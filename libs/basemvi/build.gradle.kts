plugins {
    androidLibrary
}

dependencies {

    implementation(Libs.appcompat)
    implementation(Libs.kotlinx_coroutines_core)
    implementation(Libs.kotlinx_coroutines_android)
    implementation(Libs.recyclerview)

    testImplementation(project(BuildModules.Common.testShared))

    androidTestImplementation(Libs.androidx_test_ext_junit)
    androidTestImplementation(Libs.espresso_core)
}
