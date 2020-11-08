import pwillmann.build.AndroidBuildConstants
import pwillmann.build.Dependencies
import pwillmann.build.TestDependencies

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

androidExtensions {
    isExperimental = true
}

val appVersionName: String by project

android {
    defaultConfig {
        applicationId = "pwillmann.banking.android"
        minSdkVersion(AndroidBuildConstants.minSdk)
        targetSdkVersion(AndroidBuildConstants.targetSdk)
        versionCode = 1
        versionName = appVersionName
    }
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "_DEBUG"
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            isDebuggable = true
        }
    }

    bundle {
        language {
            // We only have Dutch so no need to split by language.
            enableSplit = false
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.Common.kotlinStdlib)
    implementation(project(":banking-common-data"))

    // [ANDROIDX-MATERIAL]
    implementation(Dependencies.Android.AndroidX.activity)
    implementation(Dependencies.Android.AndroidX.appCompat)
    implementation(Dependencies.Android.AndroidX.core)
    implementation(Dependencies.Android.AndroidX.material)
    implementation(Dependencies.Android.AndroidX.fragment)

    // [Compose]
    implementation(Dependencies.Android.Compose.ui)
    implementation(Dependencies.Android.Compose.uiTooling)
    implementation(Dependencies.Android.Compose.foundation)
    implementation(Dependencies.Android.Compose.runtime)
    implementation(Dependencies.Android.Compose.livedata)
    implementation(Dependencies.Android.Compose.material)

    // [TOOLS]
    implementation(Dependencies.Android.timber)

    // [UI]
    implementation(Dependencies.Android.charts)

    // [TESTING]
    testImplementation(TestDependencies.Common.junit)
    testImplementation(TestDependencies.Common.truth)
    testImplementation(TestDependencies.Common.Mockito.core)
    testImplementation(TestDependencies.Common.Mockito.inline)
    testImplementation(TestDependencies.Common.Mockito.kotlin) {
        exclude(group = "org.jetbrains.kotlin")
        exclude(group = "org.mockito")
    }
}
