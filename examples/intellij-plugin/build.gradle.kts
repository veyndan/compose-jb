import org.jetbrains.compose.compose

plugins {
    id("org.jetbrains.intellij") version "1.3.0"
    java
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("idea")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

dependencies {
    implementation(compose.desktop.currentOs)
    testImplementation(kotlin("test"))
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version.set("2021.3")
    plugins.set(listOf("org.jetbrains.kotlin"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
