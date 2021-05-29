import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.Kotlin
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
                jvmTarget = Versions.Jvm
                freeCompilerArgs = listOf(
                    "-Xjsr305=strict",
//                    "-Xopt-in=kotlin.RequiresOptIn",
                )
            }
        }
    }
}
