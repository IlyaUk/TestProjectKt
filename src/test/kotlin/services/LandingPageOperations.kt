package services

import config.ApplicationConfig
import elements.Navigation.open
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import pages.LandingPage
import utils.Waiter

class LandingPageOperations(config: ApplicationConfig): BasePageOperations() {
  private val landingPage = LandingPage().calculator
  private val log: Logger = LogManager.getLogger(LandingPageOperations::class.simpleName)

  override val pageUrlEndpoint: String = config.landingPageServiceEndpoint

  fun getCreditAmountValue(): String? {
    return landingPage.getActualCreditAmountValue()
  }

  fun getCreditPeriodValue(): String? {
    return landingPage.getActualCreditPeriodValue()
  }

  fun setValuesOnCalculator(xOffset: Int, yOffset: Int) {
    log.info("Set credit values on calculator block")
    landingPage.apply {
      setCreditAmountSlider(xOffset, yOffset)
      setCreditPeriodSlider(xOffset, yOffset)
    }
  }

  fun setValuesOnCalculatorJS(creditAmount: String, creditPeriod: String, xOffsetMin: Double, xOffsetMax: Double) {
    log.info("Set credit values on calculator block with JS")
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