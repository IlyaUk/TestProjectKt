package driver.selenide

import com.codeborne.selenide.Configuration
import driver.config.BrowserType

class FirefoxSelenideDriverFactory(private val selenideDriverConfiguration: SelenideDriverConfiguration) :
    DefaultSelenideDriverFactory() {
  override fun configDriver() {
    Configuration.browser = BrowserType.FIREFOX.browserName
    setSelenideDefaultDriverConfig(selenideDriverConfiguration)
  }
}