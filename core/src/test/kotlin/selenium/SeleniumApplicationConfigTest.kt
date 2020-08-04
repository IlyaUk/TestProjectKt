package selenium

import driver.config.DriverConfigProvider
import driver.config.DriverFrameworkType
import driver.selenium.SeleniumDriverConfiguration
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class SeleniumApplicationConfigTest {
  private val screenResolutionRegex = Regex("[0-9]{3,4}")
  private val timeoutRegex = Regex("[0-9]{1,4}")
  private val portRegex = Regex("[0-9]{4}")
  private val hostRegex = Regex("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}\$")

  @DisplayName("Get_WebDriverConfig_From_Default_FilePath_To_Yaml")
  @Test
  fun getWebDriverObjectFromDefaultFilepath() {
    val webDriverConfig = DriverConfigProvider().getDriverConfig(DriverFrameworkType.SELENIUM) as SeleniumDriverConfiguration
    assertAll(
        { Assertions.assertTrue((webDriverConfig.height).toString().matches(screenResolutionRegex)) },
        { Assertions.assertTrue((webDriverConfig.width).toString().matches(screenResolutionRegex)) },
        { Assertions.assertTrue((webDriverConfig.defaultTimeoutMilliseconds).toString().matches(timeoutRegex)) },
        { Assertions.assertTrue((webDriverConfig.webDriverPort).toString().matches(portRegex)) },
        { Assertions.assertTrue((webDriverConfig.webDriverHost).matches(hostRegex)) }
    )
  }

  @DisplayName("Get_WebDriverConfig_From_Specified_FilePath_To_Yaml")
  @Test
  fun getWebDriverObjectFromSpecifiedFilepath() {
    val filePath = "wedbriver_configs/fake_driver_config.yaml"
    val webDriverConfig = DriverConfigProvider().getSeleniumDriverConfig(filePath)
    assertAll(
        { Assertions.assertEquals(1080, webDriverConfig.height) },
        { Assertions.assertEquals(1920, webDriverConfig.width) },
        { Assertions.assertEquals(100, webDriverConfig.defaultTimeoutMilliseconds) },
        { Assertions.assertEquals(5555, webDriverConfig.webDriverPort) },
        { Assertions.assertEquals("192.168.1.1.", webDriverConfig.webDriverHost) }
    )
  }

  @DisplayName("Get_WebDriverConfig_From_NonExisted_FilePath_To_Yaml")
  @Test
  fun getWebDriverObjectFromNonExistedFilepath() {
    val incorrectFilePath = "wedbriver_configs/driver_config1.yaml"
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      DriverConfigProvider().getSeleniumDriverConfig(incorrectFilePath)
    }
  }
}
