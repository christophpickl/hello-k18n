import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version Versions.Plugins.ShadowJar
}

dependencies {
    implementation(project(":users-service:sdk-client"))
    implementation(project(":commons:common-server"))
    implementation(Dependencies.Ktor.Core)
    implementation(Dependencies.Ktor.Netty) {
        excludeStdlibJdk7()
    }
    implementation(Dependencies.Arrow.Core)
    implementation(Dependencies.Ktor.Serialization)
    implementation(Dependencies.Kodein.Core)
    implementation(Dependencies.Kodein.Ktor)
    implementation(Dependencies.Klogging)
    implementation(Dependencies.LogBack)

    testImplementation(project(":commons:common-server-test"))
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("books-service")
    archiveClassifier.set("")
    archiveVersion.set("")
    manifest {
        attributes(mapOf("Main-Class" to "hellokube.booksService.serviceImpl.BooksServiceApp"))
    }
}
