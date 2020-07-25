package httpclient

interface HttpClient {
  fun sendGetRequest(request: Any): Any
  fun sendPostRequest(request: Any): Any
  fun closeResponse(response: Any? = null)
}