package driver.selenium

import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.util.concurrent.TimeUnit

abstract class DefaultSeleniumDriverFactory : SeleniumDriverFactory {

  protected fun setDefaultDriverConfig(driver: WebDriver, seleniumDriverConfiguration: SeleniumDriverConfiguration) {
    driver.manage().window().size = Dimension(seleniumDriverConfiguration.width, seleniumDriverConfiguration.height)
    driver.manage().timeouts().implicitlyWait(seleniumDriverConfiguration.defaultTimeoutMilliseconds, TimeUnit.SECONDS)
  }

  protected fun getGeneralDesiredCapabilities(): DesiredCapabilities {
    val desiredCapabilities = DesiredCapabilities()
    desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
    return desiredCapabilities
  }
}