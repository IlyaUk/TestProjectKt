package core

import org.openqa.selenium.WebDriver

abstract class WebDriverFactory {
  abstract fun getDriver(): WebDriver
}