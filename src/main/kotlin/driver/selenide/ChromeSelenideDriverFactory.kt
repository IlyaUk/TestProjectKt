package driver.selenide

import com.codeborne.selenide.Configuration
import driver.config.BrowserType

class ChromeSelenideDriverFactory(private val selenideDriverConfiguration: SelenideDriverConfiguration) :
    DefaultSelenideDriverFactory() {
  override fun configDriver() {
    Configuration.browser = BrowserType.CHROME.browserName
    setSelenideDefaultDriverConfig(selenideDriverConfiguration)
  }
}
