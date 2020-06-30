package services

import config.Configuration
import elements.Navigation.open
import org.openqa.selenium.WebDriver
import pages.LandingPage

class LandingPageOperations(private val driver: WebDriver, private val configObject: Configuration) {
  val landingPage = LandingPage(driver).calculator
  private var url = configObject.getURLWithAuthorization()

  fun openLandingPage() {
    configObject.apply {
      open(driver, url)
    }
  }

  fun setValuesOnCalculator(creditAmount: String, creditPeriod: String, xOffsetMin: Double, xOffsetMax: Double) {
    landingPage.apply {
      setCreditAmountSliderJS(xOffsetMin, xOffsetMax)
      setCreditPeriodSliderJS(xOffsetMin, xOffsetMax)
      setCreditValuesJS(creditAmount, creditPeriod)
    }
  }
}