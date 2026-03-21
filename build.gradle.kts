plugins {
    id("java")
    application
}

group = "kr1v.index"
version = "0.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // gson
    implementation("com.google.code.gson:gson:2.13.2")
}

application {
    mainClass.set("kr1v.index.Main")
}