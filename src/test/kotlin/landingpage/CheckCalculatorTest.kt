package landingpage

import BaseUiTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import services.LandingPageOperations
import utils.Waiter

class CheckCalculatorTest : BaseUiTest() {

  @Test
  fun `check Calculator Block`() {
    val expectedDefaultCreditAmountValue = "2,000"
    val expectedDefaultCreditPeriodValue = "30"
    val expectedMinCreditAmountValue = "1,500"
    val expectedMinCreditPeriodValue = "7"
    val expectedMaxCreditAmountValue = "5,000"
    val expectedMaxCreditPeriodValue = "30"

    LandingPageOperations(driver, configObject).apply {
      openLandingPage()

      Waiter().waitExplicitlyForElement(driver, landingPage.creditAmountSliderPoint)

      Assertions.assertEquals(expectedDefaultCreditAmountValue, landingPage.getActualCreditAmountValue())
      Assertions.assertEquals(expectedDefaultCreditPeriodValue, landingPage.getActualCreditPeriodValue())

      landingPage.setCreditAmountSlider(-100, 0)
      Assertions.assertEquals(expectedMinCreditAmountValue, landingPage.getActualCreditAmountValue())
      landingPage.setCreditAmountSlider(400, 0)
      Assertions.assertEquals(expectedMaxCreditAmountValue, landingPage.getActualCreditAmountValue())

      landingPage.setCreditPeriodSlider(-400, 0)
      Assertions.assertEquals(expectedMinCreditPeriodValue, landingPage.getActualCreditPeriodValue())
      landingPage.setCreditPeriodSlider(400, 0)
      Assertions.assertEquals(expectedMaxCreditPeriodValue, landingPage.getActualCreditPeriodValue())

      landingPage.clickTakeLoanButton()
    }
  }

  @Test
  fun `check Calculator Block With JS`() {
    val creditAmount = "4,000"
    val creditPeriod = "25"
    val xOffsetMin = 0.0
    val xOffsetMax = 100.0

    LandingPageOperations(driver, configObject).apply {
      openLandingPage()

      Waiter().waitFluentlyForElement(driver, landingPage.creditAmountSliderPoint)

      setValuesOnCalculator(creditAmount, creditPeriod, xOffsetMin, xOffsetMax)

      Assertions.assertEquals(creditAmount, landingPage.getActualCreditAmountValue())
      Assertions.assertEquals(creditPeriod, landingPage.getActualCreditPeriodValue())

      landingPage.clickTakeLoanButtonJS()
    }
  }
}