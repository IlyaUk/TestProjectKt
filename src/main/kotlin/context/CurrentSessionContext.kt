package context

import okhttp3.Request
import okhttp3.Response

object CurrentSessionContext {
  var rawRequest: Request? = null
  var rawResponse: Response? = null
    set(value) {
      field = value
      value?.headers?.toMultimap()?.forEach {
        if (it.key == "set-cookie" && it.value.isNotEmpty()) {
          authUserToken = it.value[1]
          jsessionId = it.value[0]
        }
      }
    }
  var authUserToken: String? = null
  var jsessionId: String? = null

  fun updateResponse(response: Response) {
    rawResponse = response
  }

  fun updateRequest(request: Request) {
    rawRequest = request
  }
}