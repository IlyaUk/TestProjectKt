package core

import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit

class FirefoxDriverFactory(private val driverConfiguration: WebDriverConfiguration) : WebDriverFactory() {
  override fun getDriver(): WebDriver {
    System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\selenium_drivers\\geckodriver.exe")
    val driver: WebDriver = FirefoxDriver()
    driver.manage().window().size = Dimension(driverConfiguration.width, driverConfiguration.height)
    driver.manage().timeouts().implicitlyWait(driverConfiguration.defaultTimeoutSec, TimeUnit.SECONDS)
    return driver
  }
}