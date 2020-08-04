package driver.selenium

import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

class FirefoxSeleniumDriverFactory(private val seleniumDriverConfiguration: SeleniumDriverConfiguration) : DefaultSeleniumDriverFactory() {
  override fun getDriver(): WebDriver {
    configDriverProperties()

    val driver: WebDriver = FirefoxDriver(createCapability())

    setDefaultDriverConfig(driver, seleniumDriverConfiguration)
    return driver
  }

  private fun configDriverProperties() {
    System.setProperty("selenium.gecko.selenium", "src\\test\\resources\\selenium_drivers\\geckodriver.exe")
  }

  override fun createCapability(): FirefoxOptions {
    val firefoxOptions = FirefoxOptions()
    firefoxOptions.merge(getGeneralDesiredCapabilities())
    return firefoxOptions
  }
}