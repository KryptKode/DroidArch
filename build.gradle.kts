// Top-level build file where you can add configuration options common to all sub-projects/modules.
@file:Suppress("UNUSED_VARIABLE")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    val kotlin_version by extra("1.4.21")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Libs.com_android_tools_build_gradle)
        classpath(Libs.kotlin_gradle_plugin)
        classpath(Libs.hilt_android_gradle_plugin)
        classpath(Libs.navigation_safe_args_gradle_plugin)
    }
}

plugins.apply(ScriptsPlugins.gitHooks)

plugins {
    buildSrcVersions
}

allprojects {
    repositories {
        google()
        jcenter()
    }

    plugins.apply(ScriptsPlugins.quality)

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs +=
            "-Xuse-experimental=" +
            "kotlin.Experimental," +
            "-Xopt-in=kotlin.RequiresOptIn," +
            "kotlinx.coroutines.ExperimentalCoroutinesApi," +
            "kotlinx.coroutines.InternalCoroutinesApi," +
            "kotlinx.coroutines.ObsoleteCoroutinesApi," +
            "kotlinx.coroutines.FlowPreview"
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
