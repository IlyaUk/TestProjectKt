package httpclient

import httpclient.okhttp.OkHttp
import okhttp3.Request
import okhttp3.Response

class HttpConnectionProvider(private val httpClient: OkHttp) {
  fun sendGetRequest(request: Any): Any {
    return httpClient.sendGetRequest(request as Request)
  }

  fun sendPostRequest(request: Any): Any {
    return httpClient.sendPostRequest(request as Request)
  }

  fun closeResponse(response: Any) {
    return httpClient.closeResponse(response as Response)
  }
}