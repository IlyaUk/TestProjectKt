package core

import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver

open class WebDriverContainer {
  enum class WebDrivers {
    Firefox, Chrome
  }

  fun getDriver(driverType: WebDrivers): WebDriver {
    var driver: WebDriver = when (driverType) {
      WebDrivers.Firefox -> FirefoxDriver()
      WebDrivers.Chrome -> ChromeDriver()
    }
    driver.manage().window().size = Dimension(1600, 900)
    return driver
  }
}