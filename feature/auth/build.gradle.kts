plugins {
    id(GradlePluginId.ANDROID_DYNAMIC_FEATURE)
    id(GradlePluginId.KOTLIN_ANDROID)
    id(GradlePluginId.KOTLIN_ANDROID_EXTENSIONS)
    id(GradlePluginId.SAFE_ARGS)
    id(GradlePluginId.JACOCO_ID)
    kotlin(GradlePluginId.KAPT)
}

apply(from = "./../../config/gradle/jacoco.gradle")
apply(from = "./../../config/gradle/common-android-dynamic-feature.gradle")

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
    implementation(project(ModuleDependency.APP))
    kapt(LibraryDependency.ROOM_COMPILER)

    addTestDependencies()
}
repositories {
    google()
}
