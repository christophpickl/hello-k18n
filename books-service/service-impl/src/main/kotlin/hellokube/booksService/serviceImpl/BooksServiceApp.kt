package hellokube.booksService.serviceImpl

import arrow.core.Either
import hellokube.commons.server.EnvironmentReader
import hellokube.usersService.sdkClient.UsersServiceKtorClient
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import mu.KotlinLogging.logger

class BooksServiceApp(
    private val port: Int
) {
    private val log = logger {}

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            BooksServiceApp(port = Konfiguration.serverPort).start()
        }
    }

    fun start() {
        log.info { "books-service is starting up on port $port ..." }
        embeddedServer(Netty, port = port) {
            configureRouting()
        }.start(wait = true)
    }
}

fun Application.configureRouting() {
    val usersClient = UsersServiceKtorClient(Konfiguration.usersServiceBaseUrl)

    routing {
        get("/") {
            call.respondText("hello k8s books!")
        }
        get("/health") {
            call.respondText("ok")
        }
        get("/booksForUser") {
            when (val home = usersClient.home()) {
                is Either.Right -> call.respondText("got from users-service: ${home.value}")
                is Either.Left -> call.response.status(HttpStatusCode.InternalServerError)
            }
        }
    }
}

object Konfiguration {
    private val log = logger {}

    val serverPort = EnvironmentReader.read("K8S_SERVER_PORT", "80").toInt()
    val usersServiceBaseUrl = EnvironmentReader.read("K8S_USERS_SERVICE_BASE_URL").also {
        log.info("Loaded USERS_SERVICE_BASE_URL='$it'")
    }
}
