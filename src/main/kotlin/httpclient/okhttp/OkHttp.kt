package httpclient.okhttp

import context.CurrentSessionContext
import httpclient.HttpClient
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class OkHttp : HttpClient {
  private val client: OkHttpClient = OkHttpClient()

  override fun sendGetRq(request: Request): Response {
    return client.newCall(request).execute()
  }

  override fun sendPostRq(request: Request): Response {
    val response = client.newCall(request).execute()
    CurrentSessionContext.apply {
      updateResponse(response)
      updateRequest(request)
    }
    return response
  }

  override fun sendPutRq(): Any {
    TODO("Not yet implemented")
  }

  override fun sendDeleteRq(): Any {
    TODO("Not yet implemented")
  }

  fun closeResponse(response: Response) {
    response.close()
  }
}