package httpclient

import httpclient.okhttp.OkHttp
import httpservices.CrmHttpOperations
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class AuthorizationInCrmTest {
  private val client: HttpClient = OkHttp()
  private val login = "admin"
  private val pass = "11111111"
  private val captcha = "11111"
  private val rememberUser = false
  private val id = 1
  private val role = "Administrator"
  private val roleId = 10
  private val expectedHttpCode = 200

  @Test
  fun `Authorize in CRM and get user's data`() {
    val rawResponse = CrmHttpOperations(client).sendAuthorizationPostRequest(login, pass, captcha, rememberUser)
    val authorizationResponse = CrmHttpOperations(client).getAuthorizationResponse(rawResponse.body!!.string())

    assertAll(
        { Assertions.assertEquals(login, authorizationResponse.userName) },
        { Assertions.assertEquals(id, authorizationResponse.id) },
        { Assertions.assertEquals(role, authorizationResponse.localizedRole) },
        { Assertions.assertEquals(roleId, authorizationResponse.roleId) }
    )

    OkHttp().closeResponse(rawResponse)
  }

  @Test
  fun `Get current user authorized in CRM`() {
    val rawResponse = CrmHttpOperations(client).sendCurrentUserGetRequest()
    val authorizationResponse = CrmHttpOperations(client).getAuthorizationResponse(rawResponse.body!!.string())

    assertAll(
        { Assertions.assertEquals(expectedHttpCode, rawResponse.code) },
        { Assertions.assertEquals(login, authorizationResponse.userName) },
        { Assertions.assertEquals(id, authorizationResponse.id) },
        { Assertions.assertEquals(role, authorizationResponse.localizedRole) },
        { Assertions.assertEquals(roleId, authorizationResponse.roleId) }
    )

    OkHttp().closeResponse(rawResponse)
  }
}