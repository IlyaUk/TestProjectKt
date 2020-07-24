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

  override fun sendGetRequest(request: Request): Response {
    return invokeHttpClientAction { client.newCall(request).execute() }
  }

  override fun sendPostRequest(request: Request): Response {
    return invokeHttpClientAction { client.newCall(request).execute() }
  }

  override fun closeResponse(response: Any?) {
    (response as Response).close()
  }

  private fun invokeHttpClientAction(clientOperation: () -> Response): Response {
    val response = clientOperation.invoke()
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