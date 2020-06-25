package driver

import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver

interface WebDriverFactory {
  fun getDriver(): WebDriver
  fun createCapability(): Capabilities
}