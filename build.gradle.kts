plugins {
    kotlin("jvm") version "2.3.10"
    id("java")
    application
}

group = "kr1v.index"
version = "0.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.google.code.gson:gson:2.13.2")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.9.1")
}

kotlin {
    jvmToolchain(25)
}

application {
    mainClass.set("kr1v.index.MainKt")
}