package httpclient

import okhttp3.Request

interface HttpClient {
  fun sendGetRq(request: Request): Any
  fun sendPostRq(request: Request): Any
  fun sendPutRq(): Any
  fun sendDeleteRq(): Any
}