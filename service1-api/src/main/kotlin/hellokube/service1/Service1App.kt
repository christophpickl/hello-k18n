package hellokube.service1

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

class Service1App {
    companion object {
        private const val port = 8080
        @JvmStatic
        fun main(args: Array<String>) {
            Service1App().start()
        }
    }

    fun start() {
        println("service1 is running ...")
        embeddedServer(Netty, port = port) {
            // TODO add some config
        }.start(wait = true)
    }
}
