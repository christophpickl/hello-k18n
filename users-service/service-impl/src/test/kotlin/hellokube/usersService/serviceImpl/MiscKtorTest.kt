package hellokube.usersService.serviceImpl

import assertk.assertThat
import assertk.assertions.isEqualTo
import hellokube.commons.commonServerTest.isStatusOk
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.server.testing.handleRequest
import org.testng.annotations.Test

@Test
class MiscKtorTest {
    fun `When get home Then return 200 and some text`() = withKtor {
        with(handleRequest(Get, "/")) {
            assertThat(response).isStatusOk()
            assertThat(response.content).isEqualTo("hello k8s users!")
        }
    }

    fun `When get health Then return 200 and some text`() = withKtor {
        with(handleRequest(Get, "/_health")) {
            assertThat(response).isStatusOk()
            assertThat(response.content).isEqualTo("ok")
        }
    }

    fun `When get ping Then return 200 and some text`() = withKtor {
        with(handleRequest(Get, "/_ping")) {
            assertThat(response).isStatusOk()
            assertThat(response.content).isEqualTo("pong")
        }
    }

}