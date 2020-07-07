package driver.selenium

import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.util.concurrent.TimeUnit

abstract class DefaultWebDriverFactory : WebDriverFactory {

  protected fun setDefaultDriverConfig(driver: WebDriver, webDriverConfiguration: WebDriverConfiguration) {
    driver.manage().window().size = Dimension(webDriverConfiguration.width, webDriverConfiguration.height)
    driver.manage().timeouts().implicitlyWait(webDriverConfiguration.defaultTimeoutMilliseconds, TimeUnit.SECONDS)
  }

  protected fun getGeneralDesiredCapabilities(): DesiredCapabilities {
    val desiredCapabilities = DesiredCapabilities()
    desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
    return desiredCapabilities
  }
}