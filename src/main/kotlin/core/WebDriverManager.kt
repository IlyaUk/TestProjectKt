package core

import org.openqa.selenium.WebDriver

class WebDriverManager {
  private val driverConfiguration = WebDriverConfigProvider().getDriverConfig()
  private fun setDriverFactory(): WebDriverFactory {
    return when (driverConfiguration.webDriverType) {
      WebDriverType.Remote -> RemoteDriverFactory(driverConfiguration)
      WebDriverType.Local ->
        when (driverConfiguration.browserType) {
          BrowserType.Chrome -> ChromeDriverFactory(driverConfiguration)
          BrowserType.Firefox -> FirefoxDriverFactory(driverConfiguration)
        }
    }
  }

  fun getDriver(): WebDriver {
    return setDriverFactory().getDriver()
  }
}