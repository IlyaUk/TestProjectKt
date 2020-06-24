package core

import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

class ChromeDriverFactory(private val driverConfiguration: WebDriverConfiguration) : WebDriverFactory() {
  override fun getDriver(): WebDriver {
    System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\selenium_drivers\\chromedriver.exe")
    val driver: WebDriver = ChromeDriver()
    driver.manage().window().size = Dimension(driverConfiguration.width, driverConfiguration.height)
    driver.manage().timeouts().implicitlyWait(driverConfiguration.defaultTimeoutSec, TimeUnit.SECONDS)
    return driver
  }
}