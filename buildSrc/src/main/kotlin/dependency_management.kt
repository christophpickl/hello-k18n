import org.gradle.api.artifacts.ModuleDependency

object Versions {
    const val Arrow = "0.13.2"
    const val AssertK = "0.24"
    const val Coroutines = "1.5.0"
    const val JsonAssert = "1.5.0"
    const val Jvm = "1.8"
    const val Kotlin = "1.5.10"
    const val Ktor = "1.5.4"
    const val Klogging = "2.0.6"

    //    const val Kodein = "7.3.1" // ever since version 7, deployment on repo is broken
    const val Kodein = "6.5.5"
    const val Logback = "1.2.3"
    const val Serialization = "1.2.1"
    const val TestNG = "7.4.0"

    object Plugins {
        const val ShadowJar = "7.0.0"
        const val Serialization = "1.5.0"
        const val Versions = "0.39.0"
    }
}

object Dependencies {
    const val Klogging = "io.github.microutils:kotlin-logging:${Versions.Klogging}"
    const val LogBack = "ch.qos.logback:logback-classic:${Versions.Logback}"
    const val Serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Serialization}"
    const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.Coroutines}"

    // TEST
    const val TestNG = "org.testng:testng:${Versions.TestNG}"
    const val JsonAssert = "org.skyscreamer:jsonassert:${Versions.JsonAssert}"
    const val Assertk = "com.willowtreeapps.assertk:assertk-jvm:${Versions.AssertK}"

    object Ktor {
        val Core = ktor()
        val Netty = ktor("server-netty")
        val Serialization = ktor("serialization")
        val Client = ktor("client-core")
        val ClientLogging = ktor("client-logging")
        val ClientApache = ktor("client-apache")
        val Test = ktor("server-test-host")

        private fun ktor(suffix: String = ""): String {
            val realSuffix = if (suffix == "") "" else "-$suffix"
            return "io.ktor:ktor$realSuffix:${Versions.Ktor}"
        }
    }

    object Kodein {
        val Core = kodein("generic-jvm")
        val Ktor = kodein("framework-ktor-server-jvm")

        private fun kodein(artifact: String) =
            "org.kodein.di:kodein-di-$artifact:${Versions.Kodein}"
    }

    object Arrow {
        val Core = arrow("core")
        val Optics = arrow("optics")
        private fun arrow(id: String) = "io.arrow-kt:arrow-$id:${Versions.Arrow}"
    }
}

fun ModuleDependency.excludeStdlibJdk7() {
    exclude(mapOf("group" to "org.jetbrains.kotlin", "module" to "kotlin-stdlib-jdk7"))
}

fun ModuleDependency.excludeKtorTest() {
    exclude(mapOf("group" to "junit", "module" to "junit"))
    exclude(mapOf("group" to "org.jetbrains.kotlin", "module" to "kotlin-test-junit"))
}
