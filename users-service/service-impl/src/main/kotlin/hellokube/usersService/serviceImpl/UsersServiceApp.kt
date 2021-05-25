package hellokube.usersService.serviceImpl

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import mu.KotlinLogging.logger

class UsersServiceApp(
    private val port: Int
) {
    private val log = logger {}

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            UsersServiceApp(port = 80).start()
        }
    }

    fun start() {
        log.info { "users-service is starting up on port $port ..." }
        embeddedServer(Netty, port = port) {
            configureRouting()
        }.start(wait = true)
    }
}

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("hello k8s users!")
        }
        get("/health") {
            call.respondText("ok")
        }
    }
}
