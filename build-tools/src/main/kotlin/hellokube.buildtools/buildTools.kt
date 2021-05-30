package hellokube.buildtools

import java.io.File

fun sayHello() {
    println("hello from build tools")
}

fun build(args: Array<String>) {
    try {
        buildUnsafe(args)
    } catch (e: Exception) {
        println("⚠️  ERROR ⚠️  ${e.message}")
    }
}

private fun buildUnsafe(args: Array<String>) {
    val commands = listOf(DependenciesCommand, BuildCommand)

    require(args.isNotEmpty()) { "Mandatory to pass at least one argument!" }
    val commandName = args[0]
    val command = commands.firstOrNull { it.id == commandName } ?: throw Exception(
        "Invalid command name '$commandName'! Available are:\n${
            commands.joinToString("\n") { "  ${it.id} - ${it.description}" }
        }"
    )

    command.execute(args.drop(1).toList())
}

abstract class Command(
    val id: String,
    val description: String,
) {
    abstract fun execute(arguments: List<String>)
}

enum class Module(
    val cliId: String,
    val gradleId: String,
) {
    UsersService("users", ":users-service:service-impl");

    companion object {
        private val byCliId by lazy {
            values().associateBy { it.cliId }
        }

        fun byCli(id: String) =
            byCliId[id] ?: throw Exception("Unknown module ID: $id! Available: ${Module.values().joinToString()}")
    }
}

object DependenciesCommand : Command("deps", "print dependency tree for given module") {
    private val targetFile = File("build/dependencies.txt")

    override fun execute(arguments: List<String>) {
        require(arguments.isNotEmpty()) { "Mandatory to pass module name." }
        val module = Module.byCli(arguments[0])
        println("Printing dependencies for: $module")
    }
}
/*
./gradlew  :users-service:service-impl:dependencies > build/dependencies.txt
open build/dependencies.txt
 */

object BuildCommand : Command("build", "build JAR and docker image for given module") {
    override fun execute(arguments: List<String>) {
        println("build")
    }
}
