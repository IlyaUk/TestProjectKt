package driver.selenide

import driver.config.BrowserType
import driver.config.DriverConfigProvider
import driver.config.DriverFrameworkType
import driver.config.WebDriverType

class SelenideDriverManager {
  private val selenideDriverConfiguration: SelenideDriverConfiguration = DriverConfigProvider().getDriverConfig(DriverFrameworkType
      .SELENIDE) as SelenideDriverConfiguration

  private fun setDriverFactory(): SelenideDriverFactory {
    return when (selenideDriverConfiguration.webDriverType) {
      WebDriverType.REMOTE -> RemoteSelenideDriverFactory(
          selenideDriverConfiguration)
      WebDriverType.LOCAL ->
        when (selenideDriverConfiguration.browserType) {
          BrowserType.CHROME -> ChromeSelenideDriverFactory(
              selenideDriverConfiguration)
          BrowserType.FIREFOX -> FirefoxSelenideDriverFactory(
              selenideDriverConfiguration)
        }
    }
  }

  fun setSelenideWebDriverConfiguration() {
    setDriverFactory().startDriver()
  }
}