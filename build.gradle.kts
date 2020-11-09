import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import pwillmann.build.AndroidBuildConstants
import pwillmann.build.Versions

buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    }

    dependencies {
        classpath("org.jetbrains.compose:compose-gradle-plugin:${pwillmann.build.Versions.jetBrainsCompose}")
        classpath("com.android.tools.build:gradle:4.2.0-alpha16")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${pwillmann.build.Versions.kotlin}")
    }
}

plugins {
    id("com.diffplug.spotless") version "5.6.1"
    id("com.github.ben-manes.versions") version "0.33.0"
}

allprojects {
    repositories {
        // Apparently order matters, google should be first.
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://kotlin.bintray.com/kotlinx/") } // soon will be just jcenter()
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    }
}

subprojects {
    project.pluginManager.apply("com.diffplug.spotless")

    spotless {
        java {
            // This is required otherwise the code in android modules isn't picked up by spotless.
            target("**/*.java")
            targetExclude("**/build/**/*.java")
            trimTrailingWhitespace()
            removeUnusedImports()
            googleJavaFormat()
        }

        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint("0.39.0").userData(
                hashMapOf(
                    "indent_size" to "4",
                    "android" to "true",
                    "max_line_length" to "200"
                )
            )
        }

        kotlinGradle {
            target("**/*.gradle.kts")
            ktlint("0.39.0").userData(
                hashMapOf(
                    "indent_size" to "4",
                    "android" to "true",
                    "max_line_length" to "200"
                )
            )
        }

        format("misc") {
            target("**/.gitignore", "**/*.gradle", "**/*.md", "**/*.sh", "**/*.yml")
            trimTrailingWhitespace()
            endWithNewline()
        }
    }


    project.tasks.withType(KotlinCompile::class.java) {
        kotlinOptions {
            freeCompilerArgs = listOf(
                "-Xjsr305=strict",
                "-progressive",
                "-Xallow-jvm-ir-dependencies",
                "-Xskip-prerelease-check"
            )
            jvmTarget = "1.8"
            useIR = true
        }
    }

    project.plugins.whenPluginAdded {

        when (this) {
            is com.android.build.gradle.AppPlugin, is com.android.build.gradle.LibraryPlugin -> {
                the<BaseExtension>().apply {
                    compileSdkVersion(AndroidBuildConstants.compileSdk)

                    defaultConfig {
                        minSdkVersion(AndroidBuildConstants.minSdk)
                        targetSdkVersion(AndroidBuildConstants.targetSdk)
                    }

                    packagingOptions {
                        exclude("META-INF/*.kotlin_module")
                    }
                    buildFeatures.compose = true
                    buildFeatures.viewBinding = true

                    composeOptions {
                        kotlinCompilerExtensionVersion = Versions.compose
                        kotlinCompilerVersion = Versions.kotlin
                    }

                    compileOptions {
                        sourceCompatibility = JavaVersion.VERSION_1_8
                        targetCompatibility = JavaVersion.VERSION_1_8
                    }
                    lintOptions.lintConfig = rootProject.file("lint.xml")

                }
            }
            is org.gradle.api.plugins.JavaPlugin -> {
                the<JavaPluginConvention>().apply {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }
            }
            is org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin -> {
                the<KaptExtension>().apply {
                    useBuildCache = true
                }

            }

        }
    }
}

tasks.named(
    "dependencyUpdates",
    com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask::class.java
).configure {
    // Disallow non-stable candidates as upgradable versions from stable versions
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword =
        listOf("RELEASE", "FINAL", "GA", "BETA", "RC").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

val ExtensionAware.isCi: Boolean
    get() = extra.properties["isCi"].toString().toBoolean()

val ExtensionAware.isBeta: Boolean
    get() = extra.properties["isBeta"].toString().toBoolean()

