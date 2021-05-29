package hellokube.usersService.sdkClient

import arrow.core.Either
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import mu.KotlinLogging.logger

interface UsersServiceClient {
    suspend fun home(): Either<ClientError, String>
    suspend fun ping(): Either<ClientError, String>
    suspend fun health(): Either<ClientError, String>
    fun close()
}

class UsersServiceKtorClient(
    private val baseUrl: String,
) : UsersServiceClient {

    private val log = logger {}

    private val client = HttpClient(Apache) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
        engine {
            socketTimeout = 1_000
            connectTimeout = 1_000
            connectionRequestTimeout = 5_000
        }
    }

    override suspend fun home(): Either<ClientError, String> =
        get("/").okBodyOrElseThrow()

    override suspend fun health(): Either<ClientError, String> =
        get("/_health").okBodyOrElseThrow()

    override suspend fun ping(): Either<ClientError, String> =
        get("/_ping").okBodyOrElseThrow()

    override fun close() {
        log.info { "close() " }
        client.close()
    }

    private suspend fun get(path: String): HttpResponse {
        val fullPath = "$baseUrl$path"
        log.debug { "GET $fullPath" }
        return client.request(fullPath) {
            method = HttpMethod.Get
        }
    }

    private suspend fun HttpResponse.okBodyOrElseThrow(): Either<ClientError, String> {
        if (status != HttpStatusCode.OK) {
            return Either.Left(ClientError.InvalidResponseError(status.value))
        }
        return Either.Right(readText())
    }
}

sealed class ClientError {
    class InvalidResponseError(val statusCode: Int) : ClientError()
}
