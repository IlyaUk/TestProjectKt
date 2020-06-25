package webdriver

import driver.BrowserType
import driver.WebDriverConfigProvider
import driver.WebDriverType
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class GetWebDriverObjectTest {
  @DisplayName("Create_WebDriver_Object_From_Yaml")
  @Test
  fun getWebDriverObject() {
    val webDriverObject = WebDriverConfigProvider().getDriverConfig()
    assertAll(
        { assertEquals(900, webDriverObject.height) },
        { assertEquals(1600, webDriverObject.width) },
        { assertEquals(10, webDriverObject.defaultTimeoutSec) },
        { assertEquals(4444, webDriverObject.webDriverPort) },
        { assertEquals("127.0.0.1", webDriverObject.webDriverHost) },
        { Assertions.assertTrue(webDriverObject.webDriverType == WebDriverType.LOCAL || webDriverObject.webDriverType ==
            WebDriverType.REMOTE) },
        { Assertions.assertTrue(webDriverObject.browserType == BrowserType.FIREFOX || webDriverObject.browserType ==
            BrowserType.CHROME) }
    )
  }
}
