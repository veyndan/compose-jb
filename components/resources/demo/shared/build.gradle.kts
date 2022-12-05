import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    kotlin("multiplatform")
    id("app.cash.paparazzi") version "1.1.0"
    id("com.android.library")
    id("org.jetbrains.compose")
}

version = "1.0-SNAPSHOT"

kotlin {
    android()
    jvm("desktop")
    js(IR) {
        browser {
            commonWebpackConfig {
                resolveFromModulesFirst = true
                sourceMaps = true
            }
        }
        binaries.executable()
    }
    macosX64 {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
    macosArm64 {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.material)
                // https://github.com/JetBrains/compose-jb/issues/2106#issuecomment-1175423454
//                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                implementation(project(":resources:library"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.8.0-alpha03")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.21")
                implementation("com.google.testparameterinjector:test-parameter-injector:1.10")
                implementation("androidx.core:core-ktx") {
                    version { strictly("1.8.0") }
                }
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
        val macosMain by creating {
            dependsOn(commonMain)
        }
        val macosX64Main by getting {
            dependsOn(macosMain)
        }
        val macosArm64Main by getting {
            dependsOn(macosMain)
        }
    }
}

android {
    namespace = "org.jetbrains.compose.resources.demo.shared"
    compileSdk = 32
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
}

compose.experimental {
    web.application {}
}
