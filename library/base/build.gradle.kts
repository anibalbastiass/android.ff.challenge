plugins {
    id(GradlePluginId.ANDROID_LIBRARY)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_ANDROID_EXTENSIONS)
    kotlin(GradlePluginId.KAPT)
}

apply(from = "./../../config/gradle/common-android-core-library.gradle")

android {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(AndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(AndroidConfig.TARGET_SDK_VERSION)

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }
}

kapt {
    generateStubs = true
}

dependencies {
    api(LibraryDependency.KOTLIN)
    api(LibraryDependency.KOTLIN_REFLECT)
    api(LibraryDependency.KODEIN)
    api(LibraryDependency.KODEIN_ANDROID_X)
    api(LibraryDependency.NAVIGATION_FRAGMENT_KTX)
    api(LibraryDependency.NAVIGATION_UI_KTX)
    api(LibraryDependency.NAVIGATION_DYNAMIC_FEATURE_FRAGMENT_KTX)
    api(LibraryDependency.TIMBER)
    api(LibraryDependency.APP_COMPAT)
    api(LibraryDependency.COROUTINES_ANDROID)
    api(LibraryDependency.CORE_KTX)
    api(LibraryDependency.FRAGMENT_KTX)
    api(LibraryDependency.LIFECYCLE_EXTENSIONS)
    api(LibraryDependency.LIFECYCLE_VIEW_MODEL_KTX)
    api(LibraryDependency.COIL)
    kapt(LibraryDependency.LIFECYCLE_COMPILER)

    addTestDependencies()
}
