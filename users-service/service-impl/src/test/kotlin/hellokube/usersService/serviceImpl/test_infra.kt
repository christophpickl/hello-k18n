package hellokube.usersService.serviceImpl

import io.ktor.application.Application
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.withTestApplication

fun withKtor(testBody: TestApplicationEngine.() -> Unit) {
    withTestApplication(Application::configure) {
        testBody()
    }
}
