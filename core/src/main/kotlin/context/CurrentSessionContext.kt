package context

import httpclient.utils.HeaderType
import okhttp3.Request
import okhttp3.Response

object CurrentSessionContext {
  var rawRequest: Request? = null
  var rawResponse: Response? = null
    set(value) {
      field = value
      value?.headers?.toMultimap()?.forEach {
        if (it.key == HeaderType.SET_COOKIE.headerName && it.value.isNotEmpty()) {
          authUserToken = getCookieValue(it.value, "AuthUser", authUserToken)
          jsessionId = getCookieValue(it.value, "JSESSIONID", jsessionId)
        }
      }
    }
  var authUserToken: String = ""
  var jsessionId: String = ""

  fun updateResponse(response: Response) {
    rawResponse = response
  }

  fun updateRequest(request: Request) {
    rawRequest = request
  }

  private fun getCookieValue(cookiesMap: List<String>, cookieName: String, actualCookieValue: String): String {
    val resultsList: List<String> = cookiesMap.filter { it.startsWith(cookieName) }
    val resultCookieValue: String
    resultCookieValue = if (resultsList.isNotEmpty()) {
      resultsList[0]
    } else {
      actualCookieValue
    }
    return resultCookieValue
  }
}