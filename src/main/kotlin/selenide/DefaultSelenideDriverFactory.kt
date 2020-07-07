package selenide

import com.codeborne.selenide.Configuration

abstract class DefaultSelenideDriverFactory : DriverFactory {
  protected fun setSelenideDefaultDriverConfig(driverConfiguration: DriverConfiguration) {
    Configuration.startMaximized = driverConfiguration.browserStartMaximize
    Configuration.browserSize = driverConfiguration.browserScreenSize
    Configuration.timeout = driverConfiguration.defaultTimeoutMilliseconds
    Configuration.pageLoadStrategy = driverConfiguration.browserPageLoadStrategy
    Configuration.headless = driverConfiguration.headlessMode
  }

  override fun startDriver() {
    configDriver()
  }
}