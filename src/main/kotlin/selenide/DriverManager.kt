package selenide

class DriverManager {
  private val driverConfiguration = DriverConfigProvider().getDriverConfig()

  private fun setDriverFactory(): DriverFactory {
    return when (driverConfiguration.webDriverType) {
      DriverType.REMOTE -> RemoteSelenideDriverFactory(driverConfiguration)
      DriverType.LOCAL ->
        when (driverConfiguration.browserType) {
          BrowserType.CHROME -> ChromeSelenideDriverFactory(driverConfiguration)
          BrowserType.FIREFOX -> FirefoxSelenideDriverFactory(driverConfiguration)
        }
    }
  }

  fun setSelenideWebDriverConfiguration() {
    setDriverFactory().startDriver()
  }
}