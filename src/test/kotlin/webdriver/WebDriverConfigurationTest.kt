package webdriver

import driver.WebDriverConfigProvider
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class WebDriverConfigurationTest {
  private val screenResolutionRegex = Regex("[0-9]{3,4}")
  private val timeoutRegex = Regex("[0-9]{1,4}")
  private val portRegex = Regex("[0-9]{4}")
  private val hostRegex = Regex("^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}\$")

  @DisplayName("Get_WebDriverConfig_From_Default_FilePath_To_Yaml")
  @Test
  fun getWebDriverObjectFromDefaultFilepath() {
    val webDriverObject = WebDriverConfigProvider().getDriverConfig()
    assertAll(
        { Assertions.assertTrue((webDriverObject.height).toString().matches(screenResolutionRegex)) },
        { Assertions.assertTrue((webDriverObject.width).toString().matches(screenResolutionRegex)) },
        { Assertions.assertTrue((webDriverObject.defaultTimeoutSec).toString().matches(timeoutRegex)) },
        { Assertions.assertTrue((webDriverObject.webDriverPort).toString().matches(portRegex)) },
        { Assertions.assertTrue((webDriverObject.webDriverHost).matches(hostRegex)) }
    )
  }

  @DisplayName("Get_WebDriverConfig_From_Specified_FilePath_To_Yaml")
  @Test
  fun getWebDriverObjectFromSpecifiedFilepath() {
    val filePath = "wedbriver_configs/fake_driver_config.yaml"
    val webDriverObject = WebDriverConfigProvider().getDriverConfig(filePath)
    assertAll(
        { Assertions.assertEquals(1080, webDriverObject.height) },
        { Assertions.assertEquals(1920, webDriverObject.width) },
        { Assertions.assertEquals(100, webDriverObject.defaultTimeoutSec) },
        { Assertions.assertEquals(5555, webDriverObject.webDriverPort) },
        { Assertions.assertEquals("192.168.1.1.", webDriverObject.webDriverHost) }
    )
  }

  @DisplayName("Get_WebDriverConfig_From_NonExisted_FilePath_To_Yaml")
  @Test
  fun getWebDriverObjectFromNonExistedFilepath() {
    val incorrectFilePath = "wedbriver_configs/driver_config1.yaml"
    Assertions.assertThrows(IllegalArgumentException::class.java) {
      WebDriverConfigProvider().getDriverConfig(incorrectFilePath)
    }
  }
}
