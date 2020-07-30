package wiremock

import com.github.tomakehurst.wiremock.client.WireMock
import config.ConfigSource
import config.ConfigurationProvider
import junitconditions.EnabledIfReachable
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import wiremock.configurations.AuthorizeInCrmMockConfig

class WireMockStandaloneTest {
  private val config = ConfigurationProvider.setConfigType(ConfigSource.JSON).getConfig()
  private val wireMockClient = WireMock(config.wireMockHost, config.wireMockPort)

  @AfterEach
  fun `Remove all stubs from WireMockServer`() {
    wireMockClient.resetMappings()
  }

  @Test
  @EnabledIfReachable(url = "https://qa-delivery-mx-master.moneyman.ru/secure/new-admin/index.html#/login", timeoutMillis = 5000)
  fun `Get mocking stubs list from WireMock standalone server`() {
    val authorizeInCrmStaticMock = AuthorizeInCrmMockConfig(config)
    WireMockService(config).setMock(authorizeInCrmStaticMock)

    Assertions.assertTrue(wireMockClient.getStubMapping(authorizeInCrmStaticMock.id).isPresent)
  }
}