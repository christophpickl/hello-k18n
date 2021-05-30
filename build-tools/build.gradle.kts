import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.Kotlin
    id("maven-publish")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = Versions.Jvm
            freeCompilerArgs = listOf(
                "-Xjsr305=strict",
            )
        }
    }
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>(project.name) {
            from(components["java"])
            groupId = "com.github.cpickl.hellok8s"
            artifactId = "build-tools"
            version = "1.0-SNAPSHOT"
        }
    }
}
