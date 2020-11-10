package pwillmann.build

object Dependencies {

    object Android {
        object AndroidX {
            const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
            const val annotations = "androidx.annotation:annotation:${Versions.androidxAnnotation}"
            const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
            const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
            const val constraintLayout =
                "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
            const val core = "androidx.core:core-ktx:${Versions.androidxCore}"
            const val dynamicAnimation =
                "androidx.dynamicanimation.dynamicanimation-ktx:${Versions.dynamicAnimationKtx}"
            const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
            const val material = "com.google.android.material:material:${Versions.material}"
            const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
            const val navFragment =
                "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
            const val navTesting =
                "androidx.navigation:navigation-testing-ktx:${Versions.navigation}"
            const val navSafeArgsPlugin =
                "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
            const val preferences = "androidx.preference:preference-ktx:${Versions.preferences}"
            const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
            const val swipeRefreshLayout =
                "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
            const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
            const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

            object CameraX {
                const val core = "androidx.camera:camera-core:${Versions.camerax}"
                const val camera2 = "androidx.camera:camera-camera2:${Versions.camerax}"
                const val lifecycle = "androidx.camera:camera-lifecycle:${Versions.camerax}"
                const val view = "androidx.camera:camera-view:${Versions.camerax_view}"
            }

        }

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.compose}"
            const val uiTooling = "androidx.ui:ui-tooling:${Versions.compose}"
            const val foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
            const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
            const val livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
            const val material = "androidx.compose.material:material:${Versions.compose}"
        }

        object Firebase {
            const val bom = "com.google.firebase:firebase-bom:${Versions.firebase_bom}"
            const val analytics = "com.google.firebase:firebase-analytics-ktx"
            const val config = "com.google.firebase:firebase-config-ktx"
            const val messaging = "com.google.firebase:firebase-messaging-ktx"
            const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        }

        object Hilt {
            const val core = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
            const val coreTesting = "com.google.dagger:hilt-android-testing:${Versions.daggerHilt}"
            const val compiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"

            object AndroidX {
                const val viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01"
                const val compiler = "androidx.hilt:hilt-compiler:1.0.0-alpha01"
            }
        }

        object MvRx {
            const val core = "com.airbnb.android:mvrx:${Versions.mvrx}"
            const val testing = "com.airbnb.android:mvrx-testing:${Versions.mvrx}"
        }

        const val charts = "com.github.PhilJay:MPAndroidChart:v3.1.0"
        const val timber = "com.jakewharton.timber:timber:4.7.1"
    }

    object Desktop {
        object Compose {
            const val common = "org.jetbrains.compose.desktop:desktop:${Versions.jetBrainsCompose}"
            const val linux = "org.jetbrains.compose.desktop:desktop-jvm-linux:${Versions.jetBrainsCompose}"
            const val windows = "org.jetbrains.compose.desktop:desktop-jvm-windows:${Versions.jetBrainsCompose}"
            const val macos = "org.jetbrains.compose.desktop:desktop-jvm-macos:${Versions.jetBrainsCompose}"
            const val all = "org.jetbrains.compose.desktop:desktop-jvm-all:${Versions.jetBrainsCompose}"
        }
    }

    object Common {

        object Compose {
            const val animation = "org.jetbrains.compose.animation:animation:${Versions.jetBrainsCompose}"
            const val foundation = "org.jetbrains.compose.foundation:foundation:${Versions.jetBrainsCompose}"
            const val material = "org.jetbrains.compose.material:material:${Versions.jetBrainsCompose}"
            const val runtime = "org.jetbrains.compose.runtime:runtime:${Versions.jetBrainsCompose}"
            const val ui = "org.jetbrains.compose.ui:ui:${Versions.jetBrainsCompose}"
            const val materialIcons = "org.jetbrains.compose.material:material-icons-extended:${Versions.jetBrainsCompose}"
        }

        object Moshi {
            const val core = "com.squareup.moshi:moshi:${Versions.moshi}"
            const val adapters = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
            const val codeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
            const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        }

        object Okhttp {
            const val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
            const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        }

        object Retrofit {
            const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
            const val mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
            const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
            const val rxjava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
        }

        object KotlinX {
            const val datetime =
                "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}"
        }

        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val kotlinCommonStdlib =
            "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin}"
    }
}

object TestDependencies {

    object Android {
        object AndroidX {
            const val runner = "androidx.test:runner:${Versions.test}"
            const val rules = "androidx.test:rules:${Versions.test}"
            const val orchestrator = "androidx.test:orchestrator:${Versions.test}"
            const val core = "androidx.test:core:${Versions.test}"
            const val junit = "androidx.test.ext:junit:${Versions.testJunit}"
        }

        object Espresso {
            const val core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
            const val contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
            const val intents = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
        }

        const val barista = "com.schibsted.spain:barista:3.5.0"


    }

    object Common {
        object Test {
            const val jvm = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
            const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
            const val common = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
            const val annotationsCommon =
                "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
        }

        object Mockito {
            const val core = "org.mockito:mockito-core:${Versions.mockito}"
            const val inline = "org.mockito:mockito-inline:${Versions.mockito}"
            const val kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
        }

        const val truth = "com.google.truth:truth:1.0.1"
        const val junit = "junit:junit:4.13"

    }
}

object DebugDependencies {
    object Android {
        object Flipper {
            const val core = "com.facebook.flipper:flipper:${Versions.flipper}"
            const val network = "com.facebook.flipper:flipper-network-plugin:${Versions.flipper}"
            const val leakcanary =
                "com.facebook.flipper:flipper-leakcanary-plugin:${Versions.flipper}"
        }

        object LeakCanary {
            const val real = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
            const val instrumentation =
                "com.squareup.leakcanary:leakcanary-android-instrumentation:${Versions.leakCanary}"

            /*
             leaks entry should be used if refWatcher is used in non-debug code
             implementation AppDeps.LeakCanary.leaksentry
            */
            const val leaksentry = "com.squareup.leakcanary:leaksentry:${Versions.leakCanary}"
        }

        const val soloader = "com.facebook.soloader:soloader:0.9.0"
    }

    object Common {
    }
}
