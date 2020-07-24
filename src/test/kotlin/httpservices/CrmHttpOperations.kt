package httpservices

import config.ApplicationConfig
import context.CurrentSessionContext
import httpclient.HttpClient
import httpclient.HttpConnectionProvider
import httpclient.okhttp.OkHttp
import httpclient.request.AuthorizeInCrmRequest
import httpclient.response.AuthorizeInCrmResponse
import httpclient.utils.HeaderType
import okhttp3.Credentials
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import utils.convertObjectToJsonString
import utils.getClassObjectFromString

class CrmHttpOperations(private val config: ApplicationConfig) {
  private val httpClient: HttpClient = OkHttp()
  private val contentType = "application/json"
  private val expectedHttpCode = 200
  private val authorizationEndpoint = "https://${config.host}/secure/rest/sign/in"
  private val currentUserEndpoint = "https://${config.host}/secure/rest/sign/current-user"

  fun authorizeToCrm(): AuthorizeInCrmResponse {
    val request = getAuthorizationRequestAsJson(config.crmLogin, config.crmPass, config.crmCaptcha)

    val requestBuilder = Request.Builder()
        .url(authorizationEndpoint)
        .addHeader(HeaderType.AUTHORIZATION.headerName, Credentials.basic(config.user, config.pass.toString()))
        .addHeader(HeaderType.CONTENT_TYPE.headerName, contentType)
        .post(request.toRequestBody(contentType.toMediaTypeOrNull()))
        .build()

    val rawResponse = HttpConnectionProvider(httpClient).sendPostRequest(requestBuilder) as Response
    assert(rawResponse.code == expectedHttpCode) {
      "Response code doesn't match: \nExpected:$expectedHttpCode \nActual:${rawResponse.code}"
    }
    val authorizationResponse = getAuthorizationResponse(rawResponse.body!!.string())

    HttpConnectionProvider(httpClient).closeResponse(rawResponse)

    return authorizationResponse
  }

  fun sendCurrentUserGetRequest(): AuthorizeInCrmResponse {
    val jsessionID = CurrentSessionContext.jsessionId ?: ""
    val authUser = CurrentSessionContext.authUserToken ?: ""

    val requestBuilder = Request.Builder()
        .url(currentUserEndpoint)
        .addHeader(HeaderType.AUTHORIZATION.headerName, Credentials.basic(config.user, config.pass.toString()))
        .addHeader(HeaderType.COOKIE.headerName, jsessionID)
        .addHeader(HeaderType.COOKIE.headerName, authUser)
        .build()

    val rawResponse = HttpConnectionProvider(httpClient).sendGetRequest(requestBuilder) as Response
    assert(rawResponse.code == expectedHttpCode) {
      "Response code doesn't match: \nExpected:$expectedHttpCode \nActual:${rawResponse.code}"
    }
    val currentUserResponse = CrmHttpOperations(config).getAuthorizationResponse(rawResponse.body!!.string())

    HttpConnectionProvider(httpClient).closeResponse(rawResponse)

    return currentUserResponse
  }

  private fun getAuthorizationRequestAsJson(login: String, password: String, captcha: String): String =
      convertObjectToJsonString(AuthorizeInCrmRequest(login, password, captcha))

  private fun getAuthorizationResponse(response: String): AuthorizeInCrmResponse =
      getClassObjectFromString(response, AuthorizeInCrmResponse::class.java)
}