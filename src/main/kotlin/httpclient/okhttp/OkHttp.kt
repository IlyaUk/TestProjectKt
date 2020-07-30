package httpclient.okhttp

import context.CurrentSessionContext
import httpclient.HttpClient
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.concurrent.TimeUnit

class OkHttp : HttpClient {
  private var client: OkHttpClient? = null
  private val log: Logger = LogManager.getLogger(OkHttp::class.simpleName)
  private val defaultTimeoutMillis: Long = 5000

  private fun initHttpClient(timeout: Long = defaultTimeoutMillis): OkHttpClient {
    if (client == null) {
      client = OkHttpClient().newBuilder()
          .connectTimeout(timeout, TimeUnit.MILLISECONDS)
          .readTimeout(timeout, TimeUnit.MILLISECONDS)
          .build()
    }
    return client as OkHttpClient
  }

  fun sendGetRequest(request: Request, timeout: Long = defaultTimeoutMillis): Response {
    return invokeHttpClientAction { initHttpClient(timeout).newCall(request).execute() }
  }

  fun sendPostRequest(request: Request, timeout: Long = defaultTimeoutMillis): Response {
    return invokeHttpClientAction { initHttpClient(timeout).newCall(request).execute() }
  }

  fun closeResponse(response: Response) {
    response.close()
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