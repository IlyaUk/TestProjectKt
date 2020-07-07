package driver

import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.util.concurrent.TimeUnit

abstract class DefaultWebDriverFactory : WebDriverFactory {

  protected fun setDefaultDriverConfig(driver: WebDriver, driverConfiguration: WebDriverConfiguration) {
    driver.manage().window().size = Dimension(driverConfiguration.width, driverConfiguration.height)
    driver.manage().timeouts().implicitlyWait(driverConfiguration.defaultTimeoutMilliseconds, TimeUnit.SECONDS)
  }

  protected fun getGeneralDesiredCapabilities(): DesiredCapabilities {
    val desiredCapabilities = DesiredCapabilities()
    desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
    return desiredCapabilities
  }
}