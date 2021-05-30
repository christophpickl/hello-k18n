package hellokube.commons.server

object EnvironmentReader {
    fun read(key: String, default: String? = null): String =
        (System.getenv(key) ?: default) ?: throw Exception("Unknown environment variable: '$key'!")
}
