package driver.selenium

import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver

interface SeleniumDriverFactory {
  fun getDriver(): WebDriver
  fun createCapability(): Capabilities
}