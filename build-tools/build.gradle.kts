import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
