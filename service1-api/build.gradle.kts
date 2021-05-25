import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlin
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(Dependencies.Ktor.Core)
    implementation(Dependencies.Ktor.Netty)
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = Versions.jvm
            freeCompilerArgs = listOf(
                "-Xjsr305=strict",
//                "-Xuse-experimental=io.ktor.util.KtorExperimentalAPI",
//                "-Xuse-experimental=kotlin.contracts.ExperimentalContracts",
            )
        }
    }
}
