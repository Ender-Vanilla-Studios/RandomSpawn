plugins {
    kotlin("jvm") version "1.8.22"
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "net.Mirik9724"
version = "0.1"
val name = "RandomSpawn"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly(files("libs/craftbukkit-1.1.jar"))
    compileOnly("com.github.Mirik9724:MirikAPI:v0.1.5.9")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=compatibility")
    }
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand("version" to version)
    }
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveClassifier.set("")
    mergeServiceFiles()
}
