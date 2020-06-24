package webdriver

import core.BrowserType
import core.WebDriverConfigProvider
import core.WebDriverType
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
        { assertEquals("localhost", webDriverObject.webDriverHost) },
        { Assertions.assertTrue(webDriverObject.webDriverType == WebDriverType.Local || webDriverObject.webDriverType == WebDriverType.Remote) },
        { Assertions.assertTrue(webDriverObject.browserType == BrowserType.Firefox || webDriverObject.browserType == BrowserType.Chrome) }
    )
  }
}
