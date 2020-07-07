package services

import config.ApplicationConfig
import elements.Navigation.open
import pages.LandingPage
import utils.Waiter

class LandingPageOperations(config: ApplicationConfig): BasePageOperations() {
  private val landingPage = LandingPage().calculator

  override val pageUrlEndpoint: String = config.landingPageServiceEndpoint

  fun getCreditAmountValue(): String? {
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