package services

import config.Configuration
import elements.Navigation.open
import org.openqa.selenium.WebDriver
import pages.LandingPage
import utils.Waiter

class LandingPageOperations(private val driver: WebDriver, private val configObject: Configuration) {
  private val landingPage = LandingPage(driver).calculator
  private val url = configObject.getURLWithAuthorization()

  fun openLandingPage() {
    configObject.apply {
      open(driver, url)
    }
  }

  fun getCreditAmountValue (): String? {
    Waiter().waitFluentlyForElement(driver, landingPage.creditAmountSliderPoint)
    return landingPage.getActualCreditAmountValue()
  }
  fun getCreditPeriodValue (): String? {
    return landingPage.getActualCreditPeriodValue()
  }

  fun setValuesOnCalculator(xOffset: Int, yOffset: Int) {
    landingPage.apply {
      setCreditAmountSlider(xOffset, yOffset)
      setCreditPeriodSlider(xOffset, yOffset)
    }
  }

  fun setValuesOnCalculatorJS(creditAmount: String, creditPeriod: String, xOffsetMin: Double, xOffsetMax: Double) {
    landingPage.apply {
      setCreditAmountSliderJS(xOffsetMin, xOffsetMax)
      setCreditPeriodSliderJS(xOffsetMin, xOffsetMax)
      setCreditValuesJS(creditAmount, creditPeriod)
    }
  }

  fun clickTakeButton() {
    landingPage.clickTakeLoanButton()
  }

  fun clickTakeButtonJS() {
    landingPage.clickTakeLoanButtonJS()
  }
}