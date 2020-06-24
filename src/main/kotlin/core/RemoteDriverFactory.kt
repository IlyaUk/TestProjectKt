package core

import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL
import java.util.concurrent.TimeUnit

class RemoteDriverFactory(private val driverConfiguration: WebDriverConfiguration): WebDriverFactory() {
  override fun getDriver(): WebDriver {
    val hostURL = "http://" + driverConfiguration.webDriverHost + ":" + driverConfiguration.webDriverPort + "/wd/hub"
    val desiredCapabilities = DesiredCapabilities()
    desiredCapabilities.browserName = driverConfiguration.browserType.toString().toLowerCase()

    val driver: WebDriver = RemoteWebDriver(URL(hostURL), desiredCapabilities)
    driver.manage().window().size = Dimension(driverConfiguration.width, driverConfiguration.height)
    driver.manage().timeouts().implicitlyWait(driverConfiguration.defaultTimeoutSec, TimeUnit.SECONDS)
    return driver
  }
}