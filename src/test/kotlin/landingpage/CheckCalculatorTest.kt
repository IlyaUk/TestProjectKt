package landingpage

import BaseUiTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import reporting.TestListener
import services.LandingPageOperations

@ExtendWith(TestListener::class)
class CheckCalculatorTest : BaseUiTest() {

  @Test
  fun `check Calculator Block`() {
    val expectedDefaultCreditAmountValue = "2,000"
    val expectedDefaultCreditPeriodValue = "30"
    val expectedMinCreditAmountValue = "1,500"
    val expectedMinCreditPeriodValue = "7"
    val expectedMaxCreditAmountValue = "5,000"
    val expectedMaxCreditPeriodValue = "30"

    LandingPageOperations(config).apply {
      openPage()

      Assertions.assertEquals(expectedDefaultCreditAmountValue, getCreditAmountValue())
      Assertions.assertEquals(expectedDefaultCreditPeriodValue, getCreditPeriodValue())

      setValuesOnCalculator(-400, 0)
      Assertions.assertEquals(expectedMinCreditAmountValue, getCreditAmountValue())
      Assertions.assertEquals(expectedMinCreditPeriodValue, getCreditPeriodValue())

      setValuesOnCalculator(400, 0)
      Assertions.assertEquals(expectedMaxCreditAmountValue, getCreditAmountValue())
      Assertions.assertEquals(expectedMaxCreditPeriodValue, getCreditPeriodValue())

      clickTakeButton()
    }
  }

  @Test
  fun `check Calculator Block With JS`() {
    val creditAmount = "4,000"
    val creditPeriod = "25"
    val xOffsetMin = 0.0
    val xOffsetMax = 100.0

    LandingPageOperations(config).apply {
      openPage()

      setValuesOnCalculatorJS(creditAmount, creditPeriod, xOffsetMin, xOffsetMax)

      Assertions.assertEquals(creditAmount, getCreditAmountValue())
      Assertions.assertEquals(creditPeriod, getCreditPeriodValue())

      clickTakeButtonJS()
    }
  }
}