package wiremock

import com.github.tomakehurst.wiremock.client.WireMock
import config.ConfigSource
import config.ConfigurationProvider
import junitconditions.EnabledIfReachable
import mockcontrol.MockService
import mockcontrol.MockServiceProvider
import mockcontrol.mockconfigs.AuthorizeInCrmWireMockConfig
import mockcontrol.mockconfigs.MockConfigs
import mockcontrol.wiremock.WireMockService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class WireMockStandaloneTest {
  private val config = ConfigurationProvider.setConfigType(ConfigSource.JSON).getConfig()
  private val wireMockClient = WireMock(config.wireMockHost, config.wireMockPort)
  private val mockService: MockService = WireMockService(config)

  @AfterEach
  fun `Remove all stubs from WireMockServer`() {
    wireMockClient.resetMappings()
  }

  @Test
  @EnabledIfReachable("http://localhost:8084/__admin/mappings", 5000)
  fun `Get mocking stubs list from WireMock standalone server`() {
    val authorizeInCrmStaticMock = AuthorizeInCrmWireMockConfig
    MockServiceProvider(mockService).setMock(MockConfigs.POST_CRM_AUTHORIZE)

    Assertions.assertTrue(wireMockClient.getStubMapping(authorizeInCrmStaticMock.id).isPresent)
  }
}