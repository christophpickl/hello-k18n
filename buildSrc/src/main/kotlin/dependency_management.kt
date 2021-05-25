import org.gradle.api.artifacts.ModuleDependency

object Versions {
	const val jvm = "1.8"
	const val kotlin = "1.5.10"
	const val ktor = "1.5.4"
	const val klogging = "2.0.6"
	const val kodein = "7.3.1"
	const val logback = "1.2.3"

	object Plugins {
		const val ShadowJar = "7.0.0"
	}
}

object Dependencies {
	const val Klogging = "io.github.microutils:kotlin-logging:${Versions.klogging}"
	const val LogBack = "ch.qos.logback:logback-classic:${Versions.logback}"

	object Ktor {
		val Core = ktor()
		val Netty = ktor("server-netty")

		private fun ktor(suffix: String = ""): String {
			val realSuffix = if (suffix == "") "" else "-$suffix"
			return "io.ktor:ktor$realSuffix:${Versions.ktor}"
		}
	}

	object Kodein {
		val Core = kodein("generic-jvm")
		val Ktor = kodein("framework-ktor-server-jvm")

		private fun kodein(artifact: String) =
			"org.kodein.di:kodein-di-$artifact:${Versions.kodein}"
	}
	// kotlin-serialization
}

fun ModuleDependency.excludeStdlibJdk7() {
	exclude(mapOf("group" to "org.jetbrains.kotlin", "module" to "kotlin-stdlib-jdk7"))
}
