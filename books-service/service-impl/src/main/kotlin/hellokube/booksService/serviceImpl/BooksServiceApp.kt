package hellokube.booksService.serviceImpl

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import mu.KotlinLogging

class BooksServiceApp(
    private val port: Int
) {
    private val log = KotlinLogging.logger {}

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            BooksServiceApp(port = 80).start()
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
    routing {
        get("/") {
            call.respondText("hello k8s books!")
        }
        get("/health") {
            call.respondText("ok")
        }
    }
}
