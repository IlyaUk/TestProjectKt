package driver

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class ChromeDriverFactory(private val driverConfiguration: WebDriverConfiguration) : DefaultWebDriverFactory() {
  override fun getDriver(): WebDriver {
    configDriverProperties()

    val driver: WebDriver = ChromeDriver(createCapability())

    setDefaultDriverConfig(driver, driverConfiguration)
    return driver
  }

   private fun configDriverProperties() {
     System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\selenium_drivers\\chromedriver.exe")
   }

   override fun createCapability(): ChromeOptions {
    val chromeOptions = ChromeOptions()
    chromeOptions.merge(getGeneralDesiredCapabilities())
    return chromeOptions
  }
}