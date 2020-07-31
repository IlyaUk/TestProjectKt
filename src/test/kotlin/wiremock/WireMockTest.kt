package wiremock

import httpservices.CrmHttpOperations
import mockcontrol.MockServiceProvider
import mockcontrol.mockconfigs.AuthorizeInCrmWireMockConfig
import mockcontrol.mockconfigs.MockConfigs
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class WireMockTest : WireMockBaseTest() {
  private val username = "admin"
  private val id = 1
  private val role = "Administrator"
  private val roleId = 10

  @Test
  fun `Verify that selected mock is added`() {
    val authorizeInCrmStaticMock = AuthorizeInCrmWireMockConfig
    MockServiceProvider(mockService).setMock(MockConfigs.POST_CRM_AUTHORIZE)

    Assertions.assertTrue(wireMockServer.getStubMapping(authorizeInCrmStaticMock.id).isPresent,
        "Mock stub ${authorizeInCrmStaticMock.mockName} not added"
    )
  }

  @Test
  fun `Verify that selected mock is removed`() {
    val authorizeInCrmStaticMock = AuthorizeInCrmWireMockConfig
    MockServiceProvider(mockService).setMock(MockConfigs.POST_CRM_AUTHORIZE)
    MockServiceProvider(mockService).removeMock(MockConfigs.POST_CRM_AUTHORIZE)

    Assertions.assertFalse(wireMockServer.getStubMapping(authorizeInCrmStaticMock.id).isPresent,
        "Selected mock mapping ${authorizeInCrmStaticMock.mockName} not removed"
    )
  }

  @Test
  fun `Get CRM authorization response from stub`() {
    MockServiceProvider(mockService).setMock(MockConfigs.POST_CRM_AUTHORIZE)
    val authorizationResponse = CrmHttpOperations(config).authorizeToCrm("http://${config.wireMockHost}:${config.wireMockPort}/secure/rest/sign/in")

    assertAll(
        { Assertions.assertEquals(username, authorizationResponse.userName) },
        { Assertions.assertEquals(id, authorizationResponse.id) },
        { Assertions.assertEquals(role, authorizationResponse.localizedRole) },
        { Assertions.assertEquals(roleId, authorizationResponse.roleId) }
    )
  }
}