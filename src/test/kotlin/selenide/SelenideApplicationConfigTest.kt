package selenide

import driver.config.DriverFrameworkType
import driver.selenide.DriverConfiguration
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class SelenideApplicationConfigTest {
  private var browserScreenSizeRegex = Regex("[0-9]{1,4}x[0-9]{1,4}")
  private val timeoutRegex = Regex("[0-9]{1,4}")
  private val portRegex = Regex("[0-9]{4}")
  private val hostRegex = Regex("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}\$")
  private val browserPageLoadStrategyRegex = Regex("[a-z]{5,6}")

  @DisplayName("Get_WebDriverConfig_From_Default_FilePath_To_Yaml")
  @Test
  fun getSelenideConfigFromDefaultFilePath() {
    val selenideConfig = driver.config.DriverConfigProvider()
        .getDriverConfig(DriverFrameworkType.SELENIDE) as DriverConfiguration
    assertAll(
        { Assertions.assertTrue((selenideConfig.browserScreenSize).matches(browserScreenSizeRegex)) },
        { Assertions.assertTrue((selenideConfig.defaultTimeoutMilliseconds).toString().matches(timeoutRegex)) },
        { Assertions.assertTrue((selenideConfig.webDriverPort).toString().matches(portRegex)) },
        { Assertions.assertTrue((selenideConfig.webDriverHost).matches(hostRegex)) },
        { Assertions.assertTrue((selenideConfig.browserPageLoadStrategy).matches(browserPageLoadStrategyRegex)) }
    )
  }

  @DisplayName("Get_WebDriverConfig_From_Specified_FilePath_To_Yaml")
  @Test
  fun getSelenideConfigFromSpecifiedFilePath() {
    val filePath = "selenide_configs/fake_selenide_driver_config.yaml"
    val selenideConfig = driver.config.DriverConfigProvider().getSelenideDriverConfig(filePath)
    assertAll(
        { Assertions.assertEquals("1920x1080", selenideConfig.browserScreenSize) },
        { Assertions.assertEquals(100, selenideConfig.defaultTimeoutMilliseconds) },
        { Assertions.assertEquals(5555, selenideConfig.webDriverPort) },
        { Assertions.assertEquals("192.168.1.1.", selenideConfig.webDriverHost) },
        { Assertions.assertEquals(false, selenideConfig.headlessMode) },
        { Assertions.assertEquals(false, selenideConfig.browserStartMaximize) },
        { Assertions.assertEquals("normal", selenideConfig.browserPageLoadStrategy) }
    )
  }

  @DisplayName("Get_WebDriverConfig_From_NonExisted_FilePath_To_Yaml")
  @Test
  fun getSelenideConfigFromNonExistedFilePath() {
    val incorrectFilePath = "wedbriver_configs/driver_config1.yaml"
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      driver.config.DriverConfigProvider().getSelenideDriverConfig(incorrectFilePath)
    }
  }
}
