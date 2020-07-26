package wiremock

import config.ConfigSource
import config.ConfigurationProvider
import httpservices.CrmHttpOperations
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import wiremock.configurations.AuthorizeInCrmMockConfig

class WireMockTest : WireMockBaseTest() {
  private val username = "admin"
  private val id = 1
  private val role = "Administrator"
  private val roleId = 10
  private val config = ConfigurationProvider.setConfigType(ConfigSource.JSON).getConfig()

  @Test
  fun `Verify that selected mock is added`() {
    val authorizeInCrmStaticMock = AuthorizeInCrmMockConfig(config)
    WireMockService(config).setMock(authorizeInCrmStaticMock)

    Assertions.assertTrue(wireMockServer.getStubMapping(authorizeInCrmStaticMock.id).isPresent,
        "Mock stub ${authorizeInCrmStaticMock.mockName} not added"
    )
  }

  @Test
  fun `Verify that selected mock is removed`() {
    val authorizeInCrmStaticMock = AuthorizeInCrmMockConfig(config)
    WireMockService(config).setMock(authorizeInCrmStaticMock)
    WireMockService(config).removeMock(authorizeInCrmStaticMock)

    Assertions.assertFalse(wireMockServer.getStubMapping(authorizeInCrmStaticMock.id).isPresent,
        "Selected mock mapping ${authorizeInCrmStaticMock.mockName} not removed"
    )
  }

  @Test
  fun `Get CRM authorization response from stub`() {
    val authorizeInCrmStaticMock = AuthorizeInCrmMockConfig(config)
    WireMockService(config).setMock(authorizeInCrmStaticMock)
    val authorizationResponse = CrmHttpOperations(config).authorizeToCrm(
        "http://${config.wireMockHost}:${config.wireMockPort}/secure/rest/sign/in")

    assertAll(
        { Assertions.assertEquals(username, authorizationResponse.userName) },
        { Assertions.assertEquals(id, authorizationResponse.id) },
        { Assertions.assertEquals(role, authorizationResponse.localizedRole) },
        { Assertions.assertEquals(roleId, authorizationResponse.roleId) }
    )

    WireMockService(config).removeMock(authorizeInCrmStaticMock)
  }
}