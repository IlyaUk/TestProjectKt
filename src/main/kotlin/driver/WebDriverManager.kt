package driver

import org.openqa.selenium.WebDriver

class WebDriverManager {
  private val driverConfiguration = WebDriverConfigProvider().getDriverConfig()

  private fun setDriverFactory(): WebDriverFactory {
    return when (driverConfiguration.webDriverType) {
      WebDriverType.REMOTE -> RemoteDriverFactory(driverConfiguration)
      WebDriverType.LOCAL ->
        when (driverConfiguration.browserType) {
          BrowserType.CHROME -> ChromeDriverFactory(driverConfiguration)
          BrowserType.FIREFOX -> FirefoxDriverFactory(driverConfiguration)
        }
    }
  }

  fun getDriver(): WebDriver {
    return setDriverFactory().getDriver()
  }
}