package hellokube.usersService.serviceImpl

import hellokube.commons.server.EnvironmentReader
import hellokube.usersService.sdkModel.UserDto
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.serialization.json
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.serialization.json.Json
import mu.KotlinLogging.logger

class UsersServiceApp(
    private val port: Int
) {
    private val log = logger {}

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            UsersServiceApp(port = Konfiguration.serverPort).start()
        }
    }

    private var server: ApplicationEngine? = null

    fun start() {
        log.info { "users-service is starting up on port $port ..." }
        server = embeddedServer(Netty, port = port) {
            configure()
        }
        server?.start(wait = true)
    }

    fun stop() {
        log.info { "users-service stopping. " }
        server?.stop(2_000, 2_000) ?: throw IllegalStateException("Server was not yet started!")
    }
}

fun Application.configure() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
        })
    }
    configureRouting()
}

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("hello k8s users!")
        }
        get("/_health") {
            // check dependencies availability
            call.respondText("ok")
        }
        get("/_ping") {
            call.respondText("pong")
        }
        get("/users") {
            val user = UserDto("1")
            call.respond(user)
        }
    }
}

object Konfiguration {
    val serverPort = EnvironmentReader.read("K8S_SERVER_PORT", "80").toInt()
}
