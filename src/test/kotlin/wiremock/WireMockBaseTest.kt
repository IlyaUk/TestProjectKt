package wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import config.ConfigSource
import config.ConfigurationProvider
import mockcontrol.MockService
import mockcontrol.wiremock.WireMockService
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class WireMockBaseTest {
  protected val config = ConfigurationProvider.setConfigType(ConfigSource.JSON).getConfig()
  protected val wireMockServer: WireMockServer = WireMockServer(WireMockConfiguration.options().port(config.wireMockPort))
  protected val mockService: MockService = WireMockService(config)
  private val log: Logger = LogManager.getLogger(WireMockBaseTest::class.simpleName)

  @BeforeAll
  fun startWireMockServer() {
    wireMockServer.start()
    log.info("WireMock server is started on ${config.wireMockPort} port")
  }

  @AfterEach
  fun `Remove all stubs from WireMockServer`() {
    wireMockServer.resetAll()
    log.info("All stubs are removed from WireMock server")
  }

  @AfterAll
  fun stopWireMockServer() {
    wireMockServer.stop()
    log.info("WireMock server is stopped")
  }
}