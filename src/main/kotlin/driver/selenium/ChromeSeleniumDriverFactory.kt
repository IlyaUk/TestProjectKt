package driver.selenium

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class ChromeSeleniumDriverFactory(private val seleniumDriverConfiguration: SeleniumDriverConfiguration) : DefaultSeleniumDriverFactory() {
  override fun getDriver(): WebDriver {
    configDriverProperties()

    val driver: WebDriver = ChromeDriver(createCapability())

    setDefaultDriverConfig(driver, seleniumDriverConfiguration)
    return driver
  }

   private fun configDriverProperties() {
     System.setProperty("selenium.chrome.selenium", "src\\test\\resources\\selenium_drivers\\chromedriver.exe")
   }

   override fun createCapability(): ChromeOptions {
    val chromeOptions = ChromeOptions()
    chromeOptions.merge(getGeneralDesiredCapabilities())
    return chromeOptions
  }
}