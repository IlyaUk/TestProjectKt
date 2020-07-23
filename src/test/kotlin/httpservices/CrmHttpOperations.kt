package httpservices

import config.ConfigSource
import config.ConfigurationProvider
import context.CurrentSessionContext
import httpclient.HttpClient
import httpclient.HttpConnectionProvider
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

class CrmHttpOperations(private val httpClient: HttpClient) {
  private val baseConfig = ConfigurationProvider.setConfigType(ConfigSource.JSON).getConfig()
  private val contentType = "application/json"
  private val authorizationEndpoint = "https://${baseConfig.host}/secure/rest/sign/in"
  private val currentUserEndpoint = "https://${baseConfig.host}/secure/rest/sign/current-user"

  fun sendAuthorizationPostRequest(login: String, password: String, captcha: String, remember: Boolean): Response {
    val request = getAuthorizationRequestAsJson(login, password, captcha, remember)

    val requestBuilder = Request.Builder()
        .url(authorizationEndpoint)
        .addHeader(HeaderType.AUTHORIZATION.headerName, Credentials.basic(baseConfig.user, baseConfig.pass.toString()))
        .addHeader(HeaderType.CONTENTTYPE.headerName, contentType)
        .post(request.toRequestBody(contentType.toMediaTypeOrNull()))
        .build()

    return HttpConnectionProvider(httpClient).sendPostRq(requestBuilder) as Response
  }

  fun sendCurrentUserGetRequest(): Response {
    val jsessionID = CurrentSessionContext.jsessionId ?: ""
    val authUser = CurrentSessionContext.authUserToken ?: ""
    val requestBuilder = Request.Builder()
        .url(currentUserEndpoint)
        .addHeader(HeaderType.AUTHORIZATION.headerName, Credentials.basic(baseConfig.user, baseConfig.pass.toString()))
        .addHeader(HeaderType.COOKIE.headerName, jsessionID)
        .addHeader(HeaderType.COOKIE.headerName, authUser)
        .build()

    return HttpConnectionProvider(httpClient).sendGetRq(requestBuilder) as Response
  }

  fun getAuthorizationRequestAsJson(login: String, password: String, captcha: String,
      remember: Boolean): String {
    val authorizationData = AuthorizeInCrmRequest(login, password, captcha,
        remember)
    return convertObjectToJsonString(authorizationData)
  }

  fun getAuthorizationResponse(response: String): AuthorizeInCrmResponse {
    return getClassObjectFromString(response, AuthorizeInCrmResponse::class.java)
  }
}