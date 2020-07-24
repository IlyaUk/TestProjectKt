package httpclient

import okhttp3.Request

class HttpConnectionProvider(private val httpClient: HttpClient) : HttpClient {
  override fun sendGetRequest(request: Request): Any {
    return httpClient.sendGetRequest(request)
  }

  override fun sendPostRequest(request: Request): Any {
    return httpClient.sendPostRequest(request)
  }

  override fun closeResponse(response: Any?) {
    return httpClient.closeResponse(response)
  }
}