package wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import config.ConfigSource
import config.ConfigurationProvider
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class WireMockBaseTest {
  protected lateinit var wireMockServer: WireMockServer
  private val log: Logger = LogManager.getLogger(WireMockBaseTest::class.simpleName)
  private val config = ConfigurationProvider.setConfigType(ConfigSource.JSON).getConfig()

  @BeforeAll
  fun startWireMockServer() {
    wireMockServer = WireMockServer(WireMockConfiguration.options().port(config.wireMockPort))
    wireMockServer.start()
    log.info("WireMock server is started on ${config.wireMockPort} port")
  }

  @AfterAll
  fun stopWireMockServer() {
    wireMockServer.stop()
    log.info("WireMock server is stopped")
  }
}