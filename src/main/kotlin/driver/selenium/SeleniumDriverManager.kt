package driver.selenium

import driver.config.BrowserType
import driver.config.DriverConfigProvider
import driver.config.DriverFrameworkType
import driver.config.WebDriverType
import org.openqa.selenium.WebDriver

class SeleniumDriverManager {
  private val driverConfiguration: SeleniumDriverConfiguration = DriverConfigProvider().getDriverConfig(DriverFrameworkType
      .SELENIUM) as SeleniumDriverConfiguration

  companion object {
    private var driver: WebDriver? = null

    @Deprecated("Selenide library is in use now")
    fun getDriver(): WebDriver {
      if (driver == null) {
        driver = SeleniumDriverManager()
            .setDriverFactory().getDriver()
      }
      return driver as WebDriver
    }
  }

  private fun setDriverFactory(): SeleniumDriverFactory {
    return when (driverConfiguration.webDriverType) {
      WebDriverType.REMOTE -> RemoteSeleniumDriverFactory(
          driverConfiguration)
      WebDriverType.LOCAL ->
        when (driverConfiguration.browserType) {
          BrowserType.CHROME -> ChromeSeleniumDriverFactory(
              driverConfiguration)
          BrowserType.FIREFOX -> FirefoxSeleniumDriverFactory(
              driverConfiguration)
        }
    }
  }
}