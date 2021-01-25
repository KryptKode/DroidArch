[![Actions Status](https://github.com/KryptKode/DroidArch/workflows/android/badge.svg)](https://github.com/KryptKode/DroidArch/actions)
[![codecov](https://codecov.io/gh/KryptKode/DroidArch/branch/master/graph/badge.svg?token=R1UHNXH1CZ)](https://codecov.io/gh/KryptKode/DroidArch)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.4.21-blue.svg)](http://kotlinlang.org/)
[![AGP](https://img.shields.io/badge/AGP-4.1.0-blue)](https://developer.android.com/studio/releases/gradle-plugin)
[![Gradle](https://img.shields.io/badge/Gradle-6.5-blue)](https://gradle.org)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

# DroidArch

A simple android application that consumes data from [https://dummyapi.io/](https://dummyapi.io/) with a clean architecture, fully compatible with  [Android 11](https://developer.android.com/preview), supporting light and [dark theme](https://developer.android.com/preview/features/darktheme) (see screenshots) and completely written in Kotlin.

## Outline

- [App Walk-through](https://github.com/KryptKode/DroidArch/readme.md#app-walk-through)
- [App Installation](https://github.com/KryptKode/DroidArch/readme.md#app-installation)
- [API key](https://github.com/KryptKode/DroidArch/readme.md#app-installation)
- [Building source](https://github.com/KryptKode/DroidArch/readme.md#building-source)
- [Top Features](https://github.com/KryptKode/DroidArch/readme.md#top-features)
- [Architecture](https://github.com/KryptKode/DroidArch/readme.md#architecture)
- [Testing](https://github.com/KryptKode/DroidArch/readme.md#testing)
- [Libraries](https://github.com/KryptKode/DroidArch/readme.md#libraries)
- [Extras](https://github.com/KryptKode/DroidArch/readme.md#extras)

## App Walkthrough

The first screen in the app gives the user the option of selecting whether the data will be loaded from the real  [https://dummyapi.io/](https://dummyapi.io/)  API or a mocked server version. Sometimes [https://dummyapi.io/](https://dummyapi.io/)  may be down. 

<h4 align="center">
<img alt="Light mode screenshot" src="https://user-images.githubusercontent.com/25648077/105739233-306a1d00-5f38-11eb-9810-f1f5f2e3b669.gif" width="35%" vspace="10" hspace="10">
Light mode
<img alt="Dark mode screenshot" src="https://user-images.githubusercontent.com/25648077/105739161-1a5c5c80-5f38-11eb-81a8-175518f530be.gif" width="35%" vspace="10" hspace="10">
 Dark mode 
<br>

## App Installation

You can download the APK from [releases](https://github.com/KryptKode/DroidArch/releases).

## API key

The app consumes API from  [https://dummyapi.io/](https://dummyapi.io/). Get an api key [here](https://dummyapi.io/)

- In the `local.properties` file in your project-level directory,  add the following code to the file. Replace `YOUR_APP_KEY` with your Dummy API app ID.

```
    dummy.api.app.id=YOUR_API_KEY
```

## Building Source

To build this project, you require:

- Android Studio 4.1.0  or higher
- Gradle 6.5 or higher

## Top features

- Multi-module [clean architecture](https://www.notion.so/Farmz-822ad4a6a3b0476cae93be909c39a2b2)
- Kotlin coroutines with [Flow]()
- Dependency injection with [Dagger-Hilt](https://dagger.dev/hilt/)
- View abstracted from Fragment like [componentizationArch](https://github.com/julianomoraes/componentizationArch)
- API request with [Retrofit](http://square.github.io/retrofit)
- Local persistence with [Room](https://developer.android.com/topic/libraries/architecture/room)
- Paging with [Paging 3.0](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- CI  with [Github actions](https://github.com/features/actions)
- Code coverage with [jacoco](https://github.com/vanniktech/gradle-android-junit-jacoco-plugin) with reports uploaded to [codecov](https://codecov.io/gh/KryptKode/DroidArch/)
- Code lint check with [Ktlint](https://github.com/pinterest/ktlint) using a [gradle plugin](https://github.com/JLLeitschuh/ktlint-gradle)
- Static code analysis with [detekt](https://github.com/detekt/detekt)
- Dependency management with [buildSrc](https://gradle.org/kotlin/)  (Kotlin DSL)
- Dependency updates with [buildSrcVersions](https://jmfayard.github.io/refreshVersions/)
- [Git hooks](https://github.com/KryptKode/DroidArch/tree/master/scripts/git-hooks) to perform ktlint, detekt and lint checks before committing

## Architecture

The application follows the clean architecture concept. As software architecture involves the design decisions that are hard to change, we should take an approach that does not result in coupling of the various components of the software. This makes it easy to make changes as it scales. The presentation layer for each feature uses a minimalistic version of the MVI architecture. With this in mind, the project is organised into modules as follows:

![https://user-images.githubusercontent.com/25648077/105741486-a5d6ed00-5f3a-11eb-985d-ea5d766a1d0c.png](https://user-images.githubusercontent.com/25648077/105741486-a5d6ed00-5f3a-11eb-985d-ea5d766a1d0c.png)

### feature modules

This module contains features supported by the application. At present, only the `users` feature is implemented. Other features can be added as modules as the project scales. 

The users feature has two screens. One that shows a list of users, the other for displaying user details. The module defines both the view and presentation for the feature. The presentation is implemented using ViewModel + Fragment combination with a minimalist MVI architecture

Most MVI architecture implementations use Fragment/Activity as views. In my opinion, these components are best suited as view controllers.

In the project, the view is abstracted away from the Fragments into UIViews, which can be thought of as view components. The Fragments observe events from the ViewModel and delegates respective states to the view. 

### core module

The core module just sets up the implementation of the `data` layer of the application. It provides the `remote` and `cache` layers. Concurrency in the application is implemented using Kotlin coroutines (+ Flow) and this module provides the implementation for the dispatchers used in the application. 

### common-android module

This module contains utility classes, extension functions, custom views shared by the android related modules. Most of the android modules depend on this module. Having the common classes here prevents duplication of code. 

### testShared module

This module contains classes shared in unit tests in all modules. 

### app module

This module connects all other modules. Provides navigation implementation with the [Navigation Architecture Component](https://developer.android.com/guide/navigation/navigation-getting-started) library. 

## Testing

Testing is done with Junit4 testing framework, and with Google Truth for making assertions. [Mockk](https://github.com/mockk/mockk) is used to provide mocks in some of the tests. The tests run on the CI and the code coverage report is generated by [jacoco](https://github.com/vanniktech/gradle-android-junit-jacoco-plugin) can be tracked [here](https://codecov.io/gh/KryptKode/DroidArch/).

## Libraries

- [Material Components](https://github.com/material-components/material-components-android/)
- [Constraint Layout](https://developer.android.com/reference/android/support/constraint/ConstraintLayout)
- [Retrofit](http://square.github.io/retrofit) for REST api communication
- [Mockk](https://github.com/mockk/mockk) for mocking in tests
- [Dagger-Hilt](https://dagger.dev/hilt/) for dependency injection
- [Kotlin Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) for concurrency
- [Turbine](https://github.com/cashapp/turbine) for testing flow
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) & [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Navigation Architecture Component](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Room](https://developer.android.com/topic/libraries/architecture/room) for database
- [Glide](https://github.com/bumptech/glide) for image loading
- [ViewBinding](https://developer.android.com/topic/libraries/view-binding)
- [Paging 3.0](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
- [Kotlin Delegates](https://kotlinlang.org/docs/reference/delegated-properties.html)
- [Ktlint gradle plugin](https://github.com/JLLeitschuh/ktlint-gradle) for code lint checks
- [Detekt](https://github.com/detekt/detekt) for static code analysis

### Extras

The gradle script uses Kotlin Gradle DSL ([buildSrc](https://gradle.org/kotlin/) ) which brings Kotlin's rich language features to gradle configuration. The project also uses detekt to detect code smells and ktlint to enforce proper code style. Github actions handles continuous integration, and runs detekt, ktlint, lint and unit tests concurrently.  A pre-commit git hook  verifies the project's code style before committing code.  Test coverage reports are uploaded to [codecov](). 

**Other things to do**

- Improve presentation layer (try out [ReduxComponents](https://github.com/KryptKode/ReduxComponents))
- Write UI tests
- Improve Unit test coverage
- Improve UI/UX

**Lines of Code** <br>
<img alt="Lines of code" src="https://user-images.githubusercontent.com/25648077/105759636-efcacd80-5f50-11eb-9c10-e8c955c4f956.png" width="70%" >

