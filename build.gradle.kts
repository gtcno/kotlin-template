import de.undercouch.gradle.tasks.download.Download

plugins {
    kotlin("jvm") version "1.6.10"
    application
    id("de.undercouch.download") version "5.0.1"
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

application {
    mainClass.set("Someclasse") //fixme
}


kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.toString()))
    }
}

tasks.register<Download>("download-catalog") {
    src("https://raw.githubusercontent.com/gtcno/kotlin-template/main/gradle/libs.versions.toml")
    dest("gradle")
    overwrite(true)
}


tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.ktor.server)
    implementation(libs.bundles.ktor.client)
}