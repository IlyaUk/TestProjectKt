package driver.selenide

import com.codeborne.selenide.Configuration

abstract class DefaultSelenideDriverFactory : SelenideDriverFactory {
  protected fun setSelenideDefaultDriverConfig(selenideDriverConfiguration: SelenideDriverConfiguration) {
    Configuration.browserSize = selenideDriverConfiguration.browserScreenSize
    Configuration.timeout = selenideDriverConfiguration.defaultTimeoutMilliseconds
    Configuration.pageLoadStrategy = selenideDriverConfiguration.browserPageLoadStrategy
    Configuration.headless = selenideDriverConfiguration.headlessMode
  }

  override fun startDriver() {
    configDriver()
  }
}