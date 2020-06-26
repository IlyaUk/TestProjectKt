package webdriver

import driver.BrowserType
import driver.WebDriverConfigProvider
import driver.WebDriverType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class WebDriverConfigurationTest {
  private val screenResolutionRegex = Regex("[0-9]{3,4}")
  private val timeoutRegex = Regex("[0-9]{1,3}")
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
        { Assertions.assertTrue((webDriverObject.webDriverHost).matches(hostRegex)) },
        {
          Assertions.assertTrue(webDriverObject.webDriverType == WebDriverType.LOCAL || webDriverObject.webDriverType ==
              WebDriverType.REMOTE)
        },
        {
          Assertions.assertTrue(webDriverObject.browserType == BrowserType.FIREFOX || webDriverObject.browserType ==
              BrowserType.CHROME)
        }
    )
  }

  @DisplayName("Get_WebDriverConfig_From_Specified_FilePath_To_Yaml")
  @Test
  fun getWebDriverObjectFromSpecifiedFilepath() {
    val filePath = "wedbriver_configs/driver_config.yaml"
    val webDriverObject = WebDriverConfigProvider().getDriverConfig(filePath)
    assertAll(
        { Assertions.assertTrue((webDriverObject.height).toString().matches(screenResolutionRegex)) },
        { Assertions.assertTrue((webDriverObject.width).toString().matches(screenResolutionRegex)) },
        { Assertions.assertTrue((webDriverObject.defaultTimeoutSec).toString().matches(timeoutRegex)) },
        { Assertions.assertTrue((webDriverObject.webDriverPort).toString().matches(portRegex)) },
        { Assertions.assertTrue((webDriverObject.webDriverHost).matches(hostRegex)) },
        {
          Assertions.assertTrue(webDriverObject.webDriverType == WebDriverType.LOCAL || webDriverObject.webDriverType ==
              WebDriverType.REMOTE)
        },
        {
          Assertions.assertTrue(webDriverObject.browserType == BrowserType.FIREFOX || webDriverObject.browserType ==
              BrowserType.CHROME)
        }
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
