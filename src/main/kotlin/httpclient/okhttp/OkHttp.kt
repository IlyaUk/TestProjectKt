package httpclient.okhttp

import context.CurrentSessionContext
import httpclient.HttpClient
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class OkHttp : HttpClient {
  private val client: OkHttpClient = OkHttpClient()
  private val log: Logger = LogManager.getLogger(OkHttp::class.simpleName)
  private val expectedHttpCode = 200

  override fun sendGetRequest(request: Any): Response {
    return invokeHttpClientAction { client.newCall((request as Request)).execute() }
  }

  override fun sendPostRequest(request: Any): Response {
    return invokeHttpClientAction { client.newCall((request as Request)).execute() }
  }

  override fun closeResponse(response: Any?) {
    (response as Response).close()
  }

  private fun invokeHttpClientAction(clientOperation: () -> Response): Response {
    val response = clientOperation.invoke()
    assert(response.code == expectedHttpCode) {
      "Response code doesn't match: \nExpected:$expectedHttpCode \nActual:${response.code}"
    }
    val request = response.request

    logRequest(request)
    logResponse(response)

    CurrentSessionContext.apply {
      updateResponse(response)
      updateRequest(request)
    }
    return response
  }

  private fun logRequest(request: Request) {
    log.info(request)
  }

  private fun logResponse(response: Response) {
    log.info(response)
  }
}