package services

import config.Configuration
import elements.Navigation.open
import pages.LandingPage
import utils.Waiter

class LandingPageOperations(private val config: Configuration) {
  private val landingPage = LandingPage().calculator
  private val url = config.getURLWithAuthorization()

  fun openLandingPage() {
    config.apply {
      open(url)
    }
  }

  fun getCreditAmountValue(): String? {
    Waiter().waitFluentlyForElement(landingPage.creditAmountSliderPoint)
    return landingPage.getActualCreditAmountValue()
  }

  fun getCreditPeriodValue(): String? {
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
      setCreditAmountValueJS(creditAmount)
      setCreditPeriodValueJS(creditPeriod)
    }
  }

  fun clickTakeButton() {
    landingPage.clickTakeLoanButton()
  }

  fun clickTakeButtonJS() {
    landingPage.clickTakeLoanButtonJS()
  }
}