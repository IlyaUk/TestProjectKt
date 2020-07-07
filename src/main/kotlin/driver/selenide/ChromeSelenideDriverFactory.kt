package driver.selenide

import com.codeborne.selenide.Configuration
import driver.config.BrowserType

class ChromeSelenideDriverFactory(private val driverConfiguration: DriverConfiguration) :
    DefaultSelenideDriverFactory() {
  override fun configDriver() {
    Configuration.browser = BrowserType.CHROME.browserName
    setSelenideDefaultDriverConfig(driverConfiguration)
  }
}
