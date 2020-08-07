package httpclient

import config.ConfigSource
import config.ConfigurationProvider
import httpservices.CrmHttpOperations
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@Disabled
class AuthorizationInCrmTest {
  private val username = "admin"
  private val id = 1
  private val role = "Administrator"
  private val roleId = 10
  private val config = ConfigurationProvider.setConfigType(ConfigSource.JSON).getConfig()

  @Test
  fun `Authorize in CRM and get user's data`() {
    val authorizationResponse = CrmHttpOperations(config).authorizeToCrm()

    assertAll(
        { Assertions.assertEquals(username, authorizationResponse.userName) },
        { Assertions.assertEquals(id, authorizationResponse.id) },
        { Assertions.assertEquals(role, authorizationResponse.localizedRole) },
        { Assertions.assertEquals(roleId, authorizationResponse.roleId) }
    )
  }

  @Test
  fun `Get current user authorized in CRM`() {
    val currentUserResponse = CrmHttpOperations(config).run {
      authorizeToCrm()
      sendCurrentUserGetRequest()
    }

    assertAll(
        { Assertions.assertEquals(username, currentUserResponse.userName) },
        { Assertions.assertEquals(id, currentUserResponse.id) },
        { Assertions.assertEquals(role, currentUserResponse.localizedRole) },
        { Assertions.assertEquals(roleId, currentUserResponse.roleId) }
    )
  }
}