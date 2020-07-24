package httpclient

import okhttp3.Request

interface HttpClient {
  fun sendGetRequest(request: Request): Any
  fun sendPostRequest(request: Request): Any
  fun closeResponse(response: Any? = null)
}