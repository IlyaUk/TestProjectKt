package driver.selenide

import driver.config.BrowserType
import driver.config.DriverConfigProvider
import driver.config.DriverFrameworkType

class DriverManager {
  private val driverConfiguration: DriverConfiguration = DriverConfigProvider().getDriverConfig(DriverFrameworkType
      .SELENIDE) as DriverConfiguration

  private fun setDriverFactory(): DriverFactory {
    return when (driverConfiguration.webDriverType) {
      DriverType.REMOTE -> RemoteSelenideDriverFactory(
          driverConfiguration)
      DriverType.LOCAL ->
        when (driverConfiguration.browserType) {
          BrowserType.CHROME -> ChromeSelenideDriverFactory(
              driverConfiguration)
          BrowserType.FIREFOX -> FirefoxSelenideDriverFactory(
              driverConfiguration)
        }
    }
  }

  fun setSelenideWebDriverConfiguration() {
    setDriverFactory().startDriver()
  }
}