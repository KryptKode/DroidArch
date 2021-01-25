plugins {
    androidApplication
    kotlinAndroid
    kotlinParcelize
    kotlinKapt
    daggerHilt
}

android {
    compileSdkVersion(Config.compileSdk)

    defaultConfig {
        applicationId("com.kryptkode.droidarch")
        minSdkVersion(Config.minSdk)
        targetSdkVersion(Config.targetSdk)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        buildTypes {
            getByName(BuildTypes.RELEASE) {
                isMinifyEnabled = true
            }
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(BuildModules.core))
    implementation(project(BuildModules.navigation))
    implementation(project(BuildModules.Common.androidShared))
    implementation(project(BuildModules.Libs.baseMvi))
    implementation(project(BuildModules.Features.users))

    implementation(Libs.kotlin_stdlib)
    implementation(Libs.core_ktx)
    implementation(Libs.appcompat)
    implementation(Libs.timber)

    implementation(Libs.moshi_kotlin)

    implementation(Libs.room_ktx)
    implementation(Libs.room_runtime)
    implementation(Libs.paging_runtime_ktx)

    implementation(Libs.constraintlayout)
    implementation(Libs.material)

    implementation(Libs.navigation_fragment_ktx)
    implementation(Libs.navigation_ui_ktx)

    debugImplementation(Libs.leakcanary_android)

    implementation(Libs.hilt_android)
    kapt(Libs.hilt_android_compiler)
    implementation(Libs.hilt_lifecycle_viewmodel)
    kapt(Libs.hilt_compiler)

    testImplementation(Libs.junit_junit)
    androidTestImplementation(Libs.androidx_test_ext_junit)
    androidTestImplementation(Libs.espresso_core)
}
