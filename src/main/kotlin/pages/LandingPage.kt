package pages

import org.openqa.selenium.WebDriver

class LandingPage(private val driver: WebDriver) {
  val calculator by lazy { CalculatorBlock(driver) }
}