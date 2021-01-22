import kotlin.String

/**
 * Generated by https://github.com/jmfayard/buildSrcVersions
 *
 * Update this file with
 *   `$ ./gradlew buildSrcVersions`
 */
object Libs {
    const val kotlinx_coroutines_android: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:" +
            Versions.org_jetbrains_kotlinx_kotlinx_coroutines

    const val kotlinx_coroutines_core: String = "org.jetbrains.kotlinx:kotlinx-coroutines-core:" +
            Versions.org_jetbrains_kotlinx_kotlinx_coroutines

    const val kotlinx_coroutines_test: String = "org.jetbrains.kotlinx:kotlinx-coroutines-test:" +
            Versions.org_jetbrains_kotlinx_kotlinx_coroutines

    const val converter_moshi: String = "com.squareup.retrofit2:converter-moshi:" +
            Versions.com_squareup_retrofit2

    const val retrofit: String = "com.squareup.retrofit2:retrofit:" +
            Versions.com_squareup_retrofit2

    const val logging_interceptor: String = "com.squareup.okhttp3:logging-interceptor:" +
            Versions.com_squareup_okhttp3

    const val mockwebserver: String = "com.squareup.okhttp3:mockwebserver:" +
            Versions.com_squareup_okhttp3

    const val okhttp: String = "com.squareup.okhttp3:okhttp:" + Versions.com_squareup_okhttp3

    const val navigation_fragment_ktx: String = "androidx.navigation:navigation-fragment-ktx:" +
            Versions.androidx_navigation

    const val navigation_ui_ktx: String = "androidx.navigation:navigation-ui-ktx:" +
            Versions.androidx_navigation

    const val lifecycle_common_java8: String = "androidx.lifecycle:lifecycle-common-java8:" +
            Versions.androidx_lifecycle

    const val lifecycle_extensions: String = "androidx.lifecycle:lifecycle-extensions:" +
            Versions.androidx_lifecycle

    const val lifecycle_livedata_ktx: String = "androidx.lifecycle:lifecycle-livedata-ktx:" +
            Versions.androidx_lifecycle

    const val lifecycle_runtime_ktx: String = "androidx.lifecycle:lifecycle-runtime-ktx:" +
            Versions.androidx_lifecycle

    const val lifecycle_viewmodel_ktx: String = "androidx.lifecycle:lifecycle-viewmodel-ktx:" +
            Versions.androidx_lifecycle

    const val moshi_kotlin: String = "com.squareup.moshi:moshi-kotlin:" +
            Versions.com_squareup_moshi

    const val moshi_kotlin_codegen: String = "com.squareup.moshi:moshi-kotlin-codegen:" +
            Versions.com_squareup_moshi

    const val hilt_compiler: String = "androidx.hilt:hilt-compiler:" + Versions.androidx_hilt

    const val hilt_lifecycle_viewmodel: String = "androidx.hilt:hilt-lifecycle-viewmodel:" +
            Versions.androidx_hilt

    const val hilt_work: String = "androidx.hilt:hilt-work:" + Versions.androidx_hilt

    const val room_compiler: String = "androidx.room:room-compiler:" + Versions.androidx_room

    const val room_ktx: String = "androidx.room:room-ktx:" + Versions.androidx_room

    const val room_runtime: String = "androidx.room:room-runtime:" + Versions.androidx_room

    const val work_runtime_ktx: String = "androidx.work:work-runtime-ktx:" + Versions.androidx_work

    const val work_testing: String = "androidx.work:work-testing:" + Versions.androidx_work

    /**
     * https://developer.android.com/studio
     */
    const val com_android_tools_build_gradle: String = "com.android.tools.build:gradle:" +
            Versions.com_android_tools_build_gradle

    const val androidx_test_ext_junit: String = "androidx.test.ext:junit:" +
            Versions.androidx_test_ext_junit

    const val junit_junit: String = "junit:junit:" + Versions.junit_junit

    const val de_fayard_buildsrcversions_gradle_plugin: String =
            "de.fayard.buildSrcVersions:de.fayard.buildSrcVersions.gradle.plugin:" +
            Versions.de_fayard_buildsrcversions_gradle_plugin

    const val kotlin_scripting_compiler_embeddable: String =
            "org.jetbrains.kotlin:kotlin-scripting-compiler-embeddable:" +
            Versions.kotlin_scripting_compiler_embeddable

    const val kotlin_annotation_processing_gradle: String =
            "org.jetbrains.kotlin:kotlin-annotation-processing-gradle:" +
            Versions.kotlin_annotation_processing_gradle

    const val inline_activity_result_kotlin: String =
            "com.github.florent37:inline-activity-result-kotlin:" +
            Versions.inline_activity_result_kotlin

    const val hilt_android_gradle_plugin: String = "com.google.dagger:hilt-android-gradle-plugin:" +
            Versions.hilt_android_gradle_plugin

    const val kotlin_parcelize_compiler: String =
            "org.jetbrains.kotlin:kotlin-parcelize-compiler:" + Versions.kotlin_parcelize_compiler

    const val kotlin_parcelize_runtime: String = "org.jetbrains.kotlin:kotlin-parcelize-runtime:" +
            Versions.kotlin_parcelize_runtime

    const val hilt_android_compiler: String = "com.google.dagger:hilt-android-compiler:" +
            Versions.hilt_android_compiler

    const val kotlin_gradle_plugin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:" +
            Versions.kotlin_gradle_plugin

    const val leakcanary_android: String = "com.squareup.leakcanary:leakcanary-android:" +
            Versions.leakcanary_android

    const val pageindicatorview: String = "com.romandanylyk:pageindicatorview:" +
            Versions.pageindicatorview

    const val constraintlayout: String = "androidx.constraintlayout:constraintlayout:" +
            Versions.constraintlayout

    const val circleimageview: String = "de.hdodenhof:circleimageview:" + Versions.circleimageview

    const val espresso_core: String = "androidx.test.espresso:espresso-core:" +
            Versions.espresso_core

    const val kotlin_stdlib: String = "org.jetbrains.kotlin:kotlin-stdlib:" + Versions.kotlin_stdlib

    const val core_testing: String = "android.arch.core:core-testing:" + Versions.core_testing

    const val fragment_ktx: String = "androidx.fragment:fragment-ktx:" + Versions.fragment_ktx

    const val hilt_android: String = "com.google.dagger:hilt-android:" + Versions.hilt_android

    const val javax_inject: String = "javax.inject:javax.inject:" + Versions.javax_inject

    const val recyclerview: String = "androidx.recyclerview:recyclerview:" + Versions.recyclerview

    const val lint_gradle: String = "com.android.tools.lint:lint-gradle:" + Versions.lint_gradle

    const val viewbinding: String = "androidx.databinding:viewbinding:" + Versions.viewbinding

    const val viewpager2: String = "androidx.viewpager2:viewpager2:" + Versions.viewpager2

    const val appcompat: String = "androidx.appcompat:appcompat:" + Versions.appcompat

    const val core_ktx: String = "androidx.core:core-ktx:" + Versions.core_ktx

    const val material: String = "com.google.android.material:material:" + Versions.material

    const val ktlint: String = "com.pinterest:ktlint:" + Versions.ktlint

    const val timber: String = "com.jakewharton.timber:timber:" + Versions.timber

    const val aapt2: String = "com.android.tools.build:aapt2:" + Versions.aapt2

    const val mockk: String = "io.mockk:mockk:" + Versions.mockk

    const val truth: String = "com.google.truth:truth:" + Versions.truth
}
