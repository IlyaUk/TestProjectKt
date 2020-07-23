package context

import okhttp3.Request
import okhttp3.Response

object CurrentSessionContext {
  private var rawResponse: Response? = null
  private var rawRequest: Request? = null

  var authUserToken: String?
    get() = rawResponse?.headers?.toMultimap()?.get("Set-Cookie")?.get(1)
    set(value) {
      value?.forEach {
        if (it.toString().contains("AuthUser")) {
          authUserToken = value
        }
      }
    }
  var jsessionId: String?
    get() = rawResponse?.headers?.toMultimap()?.get("Set-Cookie")?.get(0)
    set(value) {
      value?.forEach {
        if (it.toString().contains("JSESSIONID")) {
          jsessionId = value
        }
      }
    }

  fun updateResponse(response: Response) {
    rawResponse = response
  }

  fun updateRequest(request: Request) {
    rawRequest = request
  }
}