package selenide

import com.codeborne.selenide.Configuration

class FirefoxSelenideDriverFactory(private val driverConfiguration: DriverConfiguration) :
    DefaultSelenideDriverFactory() {
  override fun configDriver() {
    Configuration.browser = BrowserType.FIREFOX.browserName
    setSelenideDefaultDriverConfig(driverConfiguration)
  }
}