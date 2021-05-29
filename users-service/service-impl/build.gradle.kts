import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version Versions.Plugins.ShadowJar
}

dependencies {
    implementation(project(":users-service:sdk-model"))
    implementation(Dependencies.Ktor.Core)
    implementation(Dependencies.Ktor.Netty) {
        excludeStdlibJdk7()
    }
    implementation(Dependencies.Ktor.Serialization)
    implementation(Dependencies.Klogging)
    implementation(Dependencies.LogBack)

    testImplementation(project(":users-service:sdk-client"))
    testImplementation(project(":commons:common-server-test"))
    testImplementation(Dependencies.Coroutines)
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("users-service")
    archiveClassifier.set("")
    archiveVersion.set("")
    manifest {
        attributes(mapOf("Main-Class" to "hellokube.usersService.serviceImpl.UsersServiceApp"))
    }
}
