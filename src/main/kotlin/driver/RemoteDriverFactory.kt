package driver

import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class RemoteDriverFactory(private val driverConfiguration: WebDriverConfiguration) : DefaultWebDriverFactory() {

  override fun getDriver(): WebDriver {
    val hostURL = "http://${driverConfiguration.webDriverHost}:${driverConfiguration.webDriverPort}/wd/hub"
    val driver: WebDriver = RemoteWebDriver(URL(hostURL), createCapability())

    setDefaultDriverConfig(driver, driverConfiguration)
    return driver
  }

  override fun createCapability(): Capabilities {
    return when (driverConfiguration.browserType) {
      BrowserType.CHROME -> ChromeDriverFactory(driverConfiguration).createCapability()
      BrowserType.FIREFOX -> FirefoxDriverFactory(driverConfiguration).createCapability()
    }
  }
}