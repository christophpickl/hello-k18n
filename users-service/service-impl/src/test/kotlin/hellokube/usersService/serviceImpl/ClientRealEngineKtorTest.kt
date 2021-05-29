package hellokube.usersService.serviceImpl

import assertk.assertThat
import assertk.assertions.isEqualTo
import hellokube.commons.commonTest.isRight
import hellokube.usersService.sdkClient.UsersServiceClient
import hellokube.usersService.sdkClient.UsersServiceKtorClient
import kotlinx.coroutines.runBlocking
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test

@Test
class ClientRealEngineKtorTest {

    private val port = 9921
    private lateinit var serviceApp: UsersServiceApp
    private lateinit var client: UsersServiceClient
    private val serverThread = Thread {
        serviceApp.start()
    }

    @BeforeClass
    fun `start server`() {
        serviceApp = UsersServiceApp(port)
        serverThread.start()
        client = UsersServiceKtorClient("http://0.0.0.0:$port")
    }

    @AfterClass
    fun `stop server`() {
        serviceApp.stop()
        serverThread.join()
        client.close()
    }

    fun `When get home Then return some body`() {
        val response = runBlocking {
            client.home()
        }
        val body = assertThat(response).isRight()
        assertThat(body).isEqualTo("hello k8s users!")
    }

    fun `When get ping Then return pong`() {
        val response = runBlocking {
            client.ping()
        }
        val body = assertThat(response).isRight()
        assertThat(body).isEqualTo("pong")
    }

    fun `When get health Then return some body`() {
        val response = runBlocking {
            client.health()
        }
        val body = assertThat(response).isRight()
        assertThat(body).isEqualTo("ok")
    }
}
