import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version Versions.Plugins.ShadowJar
}

dependencies {
    implementation(Dependencies.Ktor.Core)
    implementation(Dependencies.Ktor.Netty) {
        excludeStdlibJdk7()
    }
    implementation(Dependencies.Klogging)
    implementation(Dependencies.LogBack)
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("users-service")
    archiveClassifier.set("")
    archiveVersion.set("")
    manifest {
        attributes(mapOf("Main-Class" to "hellokube.usersService.serviceImpl.UsersServiceApp"))
    }
}

//tasks {
//    build {
//        dependsOn(shadowJar)
//    }
//}
