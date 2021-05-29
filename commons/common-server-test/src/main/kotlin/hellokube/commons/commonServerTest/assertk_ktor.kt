package hellokube.commons.commonServerTest

import assertk.Assert
import assertk.assertions.isEqualTo
import assertk.assertions.support.expected
import assertk.assertions.support.show
import assertk.fail
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.TestApplicationResponse
import io.ktor.server.testing.contentType
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode

fun Assert<TestApplicationResponse>.isStatusOk() {
    given {
        assertThat(it.status()).isEqualTo(HttpStatusCode.OK)
    }
}

// shortcut for: assertThat(response.contentType().withoutParameters()).isEqualTo(ContentType.Application.Json)
fun Assert<TestApplicationResponse>.isJsonContentType() {
    given { response ->
        val contentType = response.contentType()
        // ignoring any additional parameters
        if (contentType.match(ContentType.Application.Json)) return
        expected("to have content type ${ContentType.Application.Json} but was: ${show(contentType)}")
    }
}

fun Assert<TestApplicationResponse>.isContentJsonEquals(expectedJson: String, mode: JsonMode = JsonMode.Strict) {
    given { response ->
        val content = response.content ?: throw IllegalStateException("Response content must not be null!")
        assertThat(content).isJsonEquals(expectedJson, mode)
    }
}

fun Assert<String>.isJsonEquals(expectedJson: String, mode: JsonMode = JsonMode.Strict) {
    given {
        try {
            JSONAssert.assertEquals(expectedJson, it, mode.jsonAssertMode)
        } catch (e: JSONException) {
            fail("${e.message}\nGiven JSON: <<${tryFormat(it)}>>")
        } catch (e: AssertionError) {
            fail("${e.message}\nGiven JSON: <<${tryFormat(it)}>>")
        }
    }
}

enum class JsonMode(
    val jsonAssertMode: JSONCompareMode
) {
    /** Extensible, and non-strict array ordering. */
    IgnoreUnknown(JSONCompareMode.LENIENT),

    /** Not extensible, and non-strict array ordering. */
    Strict(JSONCompareMode.NON_EXTENSIBLE)
}

private fun tryFormat(json: String) =
    try {
        JSONObject(json).toString(2)
    } catch (e: JSONException) {
        try {
            JSONArray(json).toString(2)
        } catch (e: JSONException) {
            json
        }
    }
