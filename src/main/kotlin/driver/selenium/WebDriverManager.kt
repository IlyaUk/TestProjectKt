package driver.selenium

import driver.config.BrowserType
import driver.config.DriverConfigProvider
import driver.config.DriverFrameworkType
import driver.config.WebDriverType
import org.openqa.selenium.WebDriver

class WebDriverManager {
  private val driverConfiguration: WebDriverConfiguration = DriverConfigProvider().getDriverConfig(DriverFrameworkType
      .SELENIUM) as WebDriverConfiguration

  companion object {
    private var driver: WebDriver? = null

    @Deprecated("Selenide library is in use now")
    fun getDriver(): WebDriver {
      if (driver == null) {
        driver = WebDriverManager()
            .setDriverFactory().getDriver()
      }
      return driver as WebDriver
    }
  }

  private fun setDriverFactory(): WebDriverFactory {
    return when (driverConfiguration.webDriverType) {
      WebDriverType.REMOTE -> RemoteDriverFactory(
          driverConfiguration)
      WebDriverType.LOCAL ->
        when (driverConfiguration.browserType) {
          BrowserType.CHROME -> ChromeDriverFactory(
              driverConfiguration)
          BrowserType.FIREFOX -> FirefoxDriverFactory(
              driverConfiguration)
        }
    }
  }
}