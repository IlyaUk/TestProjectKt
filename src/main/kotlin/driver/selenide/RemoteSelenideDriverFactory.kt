package driver.selenide

import com.codeborne.selenide.Configuration
import driver.config.BrowserType
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

class RemoteSelenideDriverFactory(private val selenideDriverConfiguration: SelenideDriverConfiguration) :
    DefaultSelenideDriverFactory() {

  override fun configDriver() {
    configBrowserRelatedDriver()
    Configuration.remote = "http://${selenideDriverConfiguration.webDriverHost}:${selenideDriverConfiguration.webDriverPort}/wd/hub"
    Configuration.browserCapabilities.merge(getGeneralDesiredCapabilities())
  }

  private fun getGeneralDesiredCapabilities(): DesiredCapabilities {
    val desiredCapabilities = DesiredCapabilities()
    desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
    return desiredCapabilities
  }

  private fun configBrowserRelatedDriver() {
    return when (selenideDriverConfiguration.browserType) {
      BrowserType.CHROME -> ChromeSelenideDriverFactory(
          selenideDriverConfiguration).configDriver()
      BrowserType.FIREFOX -> FirefoxSelenideDriverFactory(
          selenideDriverConfiguration).configDriver()
    }
  }
}