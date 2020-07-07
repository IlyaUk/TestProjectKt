package driver.selenium

import driver.config.BrowserType
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class RemoteDriverFactory(private val webDriverConfiguration: WebDriverConfiguration) : DefaultWebDriverFactory() {

  override fun getDriver(): WebDriver {
    val hostURL = "http://${webDriverConfiguration.webDriverHost}:${webDriverConfiguration.webDriverPort}/wd/hub"
    val driver: WebDriver = RemoteWebDriver(URL(hostURL), createCapability())

    setDefaultDriverConfig(driver, webDriverConfiguration)
    return driver
  }

  override fun createCapability(): Capabilities {
    return when (webDriverConfiguration.browserType) {
      BrowserType.CHROME -> ChromeDriverFactory(
          webDriverConfiguration).createCapability()
      BrowserType.FIREFOX -> FirefoxDriverFactory(
          webDriverConfiguration).createCapability()
    }
  }
}