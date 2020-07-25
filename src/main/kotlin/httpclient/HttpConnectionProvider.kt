package httpclient

class HttpConnectionProvider(private val httpClient: HttpClient) {
  fun sendGetRequest(request: Any): Any {
    return httpClient.sendGetRequest(request)
  }

  fun sendPostRequest(request: Any): Any {
    return httpClient.sendPostRequest(request)
  }

  fun closeResponse(response: Any?) {
    return httpClient.closeResponse(response)
  }
}