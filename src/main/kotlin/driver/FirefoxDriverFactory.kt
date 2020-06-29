package driver

import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions

class FirefoxDriverFactory(private val driverConfiguration: WebDriverConfiguration) : DefaultWebDriverFactory() {
  override fun getDriver(): WebDriver {
    configDriverProperties()

    val driver: WebDriver = FirefoxDriver(createCapability())

    setDefaultDriverConfig(driver, driverConfiguration)
    return driver
  }

  private fun configDriverProperties() {
    System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\selenium_drivers\\geckodriver.exe")
  }

  override fun createCapability(): FirefoxOptions {
    val firefoxOptions = FirefoxOptions()
    firefoxOptions.merge(getGeneralDesiredCapabilities())
    return firefoxOptions
  }
}