package services

import config.ApplicationConfig
import pages.LandingPage
import reporting.AllureOperations.addStepToReport

class LandingPageOperations(config: ApplicationConfig) : BasePageOperations() {
  private val landingPage = LandingPage().calculator

  override val pageUrlEndpoint: String = config.landingPageServiceEndpoint

  fun getCreditAmountValue(): String? {
    return landingPage.getActualCreditAmountValue()
  }

  fun getCreditPeriodValue(): String? {
    return landingPage.getActualCreditPeriodValue()
  }

  fun setValuesOnCalculator(xOffset: Int, yOffset: Int) {
    addStepToReport("[Step][LandingPageOperations] Set credit values on calculator block")
    landingPage.apply {
      setCreditAmountSlider(xOffset, yOffset)
      setCreditPeriodSlider(xOffset, yOffset)
    }
  }

  fun setValuesOnCalculatorJS(creditAmount: String, creditPeriod: String, xOffsetMin: Double, xOffsetMax: Double) {
    addStepToReport("[Step][LandingPageOperations] Set credit values on calculator block with JS")
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