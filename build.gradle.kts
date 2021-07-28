plugins {
    kotlin("jvm") version Versions.Kotlin
    id("org.gradle.maven-publish")
    id("com.github.ben-manes.versions") version Versions.Plugins.Versions // ./gradlew dependencyUpdates
}

val reactorProjects = setOf("commons", "users-service", "books-service")

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    if (!reactorProjects.contains(project.name)) {

        val testsConfig by configurations.creating {
            extendsFrom(configurations["testImplementation"])
        }
        dependencies {
            testsConfig(sourceSets.getByName("test").output)
        }


        apply(plugin = "org.gradle.maven-publish")

        val sourcesJar by tasks.creating(Jar::class) {
            archiveClassifier.set("sources")
            from(sourceSets.getByName("main").allSource)
        }
        val testsJar by tasks.creating(Jar::class) {
            archiveClassifier.set("tests")
            from(sourceSets.getByName("test").output)
        }
        val testsSourcesJar by tasks.creating(Jar::class) {
            archiveClassifier.set("testsSources")
            from(sourceSets.getByName("test").allSource)
        }
        val baseGroupId = "com.github.cpickl.hellok8s"
        publishing {
            publications {
                create<MavenPublication>("maven") {
                    groupId = when {
                        reactorProjects.contains(project.parent?.name) -> "$baseGroupId.${project.parent?.name}"
                        else -> baseGroupId
                    }
                    artifactId = project.name
                    version = "1.0-SNAPSHOT"
                    from(components["java"])
                    artifact(sourcesJar)
                    artifact(testsJar)
                    artifact(testsSourcesJar)
                }
            }
        }
    }
}
