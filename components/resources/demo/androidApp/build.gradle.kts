plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":resources:demo:shared"))
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.ui:ui:1.3.1")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("com.google.android.material:material:1.8.0-alpha03")
}

android {
    namespace = "org.jetbrains.compose.resources.demo"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    compileSdk = 33
    defaultConfig {
        applicationId = "com.veyndan.calculator"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
