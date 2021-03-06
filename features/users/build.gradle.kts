plugins {
    androidLibrary
    kotlinKapt
    daggerHilt
}

dependencies {
    implementation(project(BuildModules.Common.androidShared))
    implementation(project(BuildModules.core))
    implementation(project(BuildModules.Libs.baseMvi))

    implementation(Libs.kotlinx_coroutines_android)
    implementation(Libs.kotlinx_coroutines_core)

    implementation(Libs.javax_inject)

    implementation(Libs.core_ktx)
    implementation(Libs.appcompat)
    implementation(Libs.vectordrawable)
    implementation(Libs.swiperefreshlayout)

    implementation(Libs.material)
    implementation(Libs.constraintlayout)

    implementation(Libs.circleimageview)
    implementation(Libs.pageindicatorview)
    implementation(Libs.viewpager2)

    implementation(Libs.paging_runtime_ktx)

    implementation(Libs.fragment_ktx)
    implementation(Libs.timber)

    implementation(Libs.paging_runtime_ktx)

    implementation(Libs.glide)
    kapt(Libs.com_github_bumptech_glide_compiler)

    implementation(Libs.lifecycle_extensions)
    implementation(Libs.lifecycle_livedata_ktx)
    implementation(Libs.lifecycle_viewmodel_ktx)
    implementation(Libs.lifecycle_runtime_ktx)
    implementation(Libs.lifecycle_common_java8)

    implementation(Libs.hilt_android)
    kapt(Libs.hilt_android_compiler)
    implementation(Libs.hilt_lifecycle_viewmodel)
    kapt(Libs.hilt_compiler)

    androidTestImplementation(Libs.androidx_test_ext_junit)
    androidTestImplementation(Libs.espresso_core)

    testImplementation(project(BuildModules.Common.testShared))
    testImplementation(Libs.junit_junit)
    testImplementation(Libs.core_testing)
    testImplementation(Libs.kotlinx_coroutines_test)
    testImplementation(Libs.truth)
    testImplementation("app.cash.turbine:turbine:0.3.0")
}
