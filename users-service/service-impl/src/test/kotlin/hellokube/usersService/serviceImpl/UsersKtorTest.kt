package hellokube.usersService.serviceImpl

import assertk.assertThat
import hellokube.commons.commonServerTest.isContentJsonEquals
import hellokube.commons.commonServerTest.isJsonContentType
import hellokube.commons.commonServerTest.isStatusOk
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.server.testing.handleRequest
import org.testng.annotations.Test

@Test
class UsersKtorTest {
    fun `When get users`() = withKtor {
        with(handleRequest(Get, "/users")) {
            assertThat(response).isStatusOk()
            assertThat(response).isJsonContentType()
            assertThat(response).isContentJsonEquals("""{ "id": "1" }""")
        }
    }
}
