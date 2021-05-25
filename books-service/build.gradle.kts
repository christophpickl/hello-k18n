import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlin
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = Versions.jvm
                freeCompilerArgs = listOf(
                    "-Xjsr305=strict",
                )
            }
        }
    }
}
