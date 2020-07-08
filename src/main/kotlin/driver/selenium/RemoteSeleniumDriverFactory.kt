package driver.selenium

import driver.config.BrowserType
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class RemoteSeleniumDriverFactory(private val seleniumDriverConfiguration: SeleniumDriverConfiguration) : DefaultSeleniumDriverFactory() {

  override fun getDriver(): WebDriver {
    val hostURL = "http://${seleniumDriverConfiguration.webDriverHost}:${seleniumDriverConfiguration.webDriverPort}/wd/hub"
    val driver: WebDriver = RemoteWebDriver(URL(hostURL), createCapability())

    setDefaultDriverConfig(driver, seleniumDriverConfiguration)
    return driver
  }

  override fun createCapability(): Capabilities {
    return when (seleniumDriverConfiguration.browserType) {
      BrowserType.CHROME -> ChromeSeleniumDriverFactory(
          seleniumDriverConfiguration).createCapability()
      BrowserType.FIREFOX -> FirefoxSeleniumDriverFactory(
          seleniumDriverConfiguration).createCapability()
    }
  }
}