import pwillmann.build.Dependencies

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(project(":banking-common-data"))
                implementation(Dependencies.Common.kotlinCommonStdlib)
                implementation(Dependencies.Common.KotlinX.datetime)
                api(Dependencies.Common.Compose.ui)
                api(Dependencies.Common.Compose.runtime)
                api(Dependencies.Common.Compose.foundation)
                api(Dependencies.Common.Compose.animation)
                api(Dependencies.Common.Compose.material)
                api(Dependencies.Common.Compose.materialIcons)
            }
        }
        named("androidMain") {
            dependencies {
                implementation(Dependencies.Common.kotlinCommonStdlib)
                api(Dependencies.Common.KotlinX.datetime)
            }
        }
        named("desktopMain") {
            resources.srcDirs("src/commonMain/resources")
            dependencies {
                api(Dependencies.Desktop.Compose.common)
                api(Dependencies.Desktop.Compose.macos)
            }
        }
    }
}

android {
    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res", "src/commonMain/resources")
        }
    }
}
