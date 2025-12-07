import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.test)
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.neronguyenvn.nerolauncher.benchmarks"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
        targetSdk = 36
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions.managedDevices.localDevices {
        create("pixel6Api33") {
            device = "Pixel 6"
            apiLevel = 33
            systemImageSource = "aosp"
        }
    }

    targetProjectPath = ":app"
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

baselineProfile {
    // This specifies the managed devices to use that you run the tests on.
    managedDevices.clear()
    managedDevices += "pixel6Api33"

    // Don't use a connected device but rely on a GMD for consistency between local and CI builds.
    useConnectedDevices = false
}

dependencies {
    implementation(libs.androidx.benchmark.macro)
}
