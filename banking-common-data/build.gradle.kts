import pwillmann.build.Dependencies
import pwillmann.build.TestDependencies

plugins {
    kotlin("multiplatform")
}

kotlin {
    jvm("android")

    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(Dependencies.Common.kotlinCommonStdlib)
                api(Dependencies.Common.KotlinX.datetime)
            }
        }
        named("commonTest") {
            dependencies {
                implementation(Dependencies.Common.kotlinCommonStdlib)
                implementation(Dependencies.Common.KotlinX.datetime)
                implementation(TestDependencies.Common.Test.annotationsCommon)
                implementation(TestDependencies.Common.Test.common)
                implementation(TestDependencies.Common.Test.junit)
            }
        }
    }
}
