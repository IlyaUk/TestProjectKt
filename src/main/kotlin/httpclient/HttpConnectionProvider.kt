package httpclient

import okhttp3.Request

class HttpConnectionProvider(private val httpClient: HttpClient) : HttpClient {
  override fun sendGetRq(request: Request): Any {
    return httpClient.sendGetRq(request)
  }

  override fun sendPostRq(request: Request): Any {
    return httpClient.sendPostRq(request)
  }

  override fun sendPutRq(): Any {
    TODO("Not yet implemented")
  }

  override fun sendDeleteRq(): Any {
    TODO("Not yet implemented")
  }
}