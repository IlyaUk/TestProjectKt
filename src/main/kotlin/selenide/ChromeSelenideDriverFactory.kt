package selenide

import com.codeborne.selenide.Configuration

class ChromeSelenideDriverFactory(private val driverConfiguration: DriverConfiguration) :
    DefaultSelenideDriverFactory() {
  override fun configDriver() {
    Configuration.browser = BrowserType.CHROME.browserName
    setSelenideDefaultDriverConfig(driverConfiguration)
  }
}
