
object Versions {
	const val jvm = "1.8"
	const val kotlin = "1.5.10"
	const val ktor = "1.5.4"
	const val klogging = "2.0.6"
	const val kodein = "7.3.1"
}

object Dependencies {
	const val Logging = "io.github.microutils:kotlin-logging:${Versions.klogging}"

	object Ktor {
		val Core = ktor()
		val Netty = ktor("server-netty")

		private fun ktor(suffix: String = ""): String {
			val realSuffix = if(suffix == "") "" else "-$suffix"
			return  "io.ktor:ktor$realSuffix:${Versions.ktor}"
		}
	}

	object Kodein {
		val Core = kodein("generic-jvm")
		val Ktor = kodein("framework-ktor-server-jvm")

		private fun kodein(artifact: String) =
			"org.kodein.di:kodein-di-$artifact:${Versions.kodein}"
	}
	// kotlin-serialization
	// testng
}
