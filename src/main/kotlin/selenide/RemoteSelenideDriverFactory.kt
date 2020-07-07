package selenide

import com.codeborne.selenide.Configuration
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

class RemoteSelenideDriverFactory(private val driverConfiguration: DriverConfiguration) :
    DefaultSelenideDriverFactory() {

  override fun configDriver() {
    configBrowserRelatedDriver()
    Configuration.remote = "http://${driverConfiguration.webDriverHost}:${driverConfiguration.webDriverPort}/wd/hub"
    Configuration.browserCapabilities.merge(getGeneralDesiredCapabilities())
  }

  private fun getGeneralDesiredCapabilities(): DesiredCapabilities {
    val desiredCapabilities = DesiredCapabilities()
    desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
    return desiredCapabilities
  }

  private fun configBrowserRelatedDriver() {
    return when (driverConfiguration.browserType) {
      BrowserType.CHROME -> ChromeSelenideDriverFactory(driverConfiguration).configDriver()
      BrowserType.FIREFOX -> FirefoxSelenideDriverFactory(driverConfiguration).configDriver()
    }
  }
}