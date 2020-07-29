package wiremock

import com.github.tomakehurst.wiremock.client.WireMock
import config.ConfigSource
import config.ConfigurationProvider
import junitconditions.EnabledIfReachable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import wiremock.configurations.AuthorizeInCrmMockConfig

class WireMockStandaloneTest {
  private val config = ConfigurationProvider.setConfigType(ConfigSource.JSON).getConfig()
  private val wireMockClient = WireMock(config.wireMockHost, config.wireMockPort)

  @Test
  @EnabledIfReachable(
      url = "http://localhost:8084/__admin/mappings/", timeoutMillis = 5000)
  fun `Get mocking stubs list from WireMock standalone server`() {
    val authorizeInCrmStaticMock = AuthorizeInCrmMockConfig(config)
    WireMockService(config).setMock(authorizeInCrmStaticMock)

    Assertions.assertTrue(wireMockClient.getStubMapping(authorizeInCrmStaticMock.id).isPresent)
  }
}