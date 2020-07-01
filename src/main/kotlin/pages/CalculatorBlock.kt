package pages

import elements.Button.clickButton
import elements.Button.clickButtonJS
import elements.input.Input.getInputValue
import elements.input.Input.setInputValueJS
import elements.Slider.setValueUsingSlider
import elements.Slider.setValueUsingSliderJS
import org.openqa.selenium.By

class CalculatorBlock {
  private val calculatorAmount: By = By.cssSelector("[data-test-id='calculator_amount']")
  private val calculatorPeriod: By = By.cssSelector("[data-test-id='calculator_days']")
  val creditAmountSliderPoint: By = By.xpath(
      "//*[@data-test-id='amount']//div[contains(@class, 'mainCalculator__slider')]/span")
  private val creditPeriodSliderPoint: By = By.xpath(
      "//*[@data-test-id='period']//div[contains(@class, 'mainCalculator__slider')]/span")
  private val creditAmountSliderLine: By = By.xpath(
      "//*[@data-test-id='amount']//div[contains(@class, 'mainCalculator__slider')]/div")
  private val creditPeriodSliderLine: By = By.xpath(
      "//*[@data-test-id='period']//div[contains(@class, 'mainCalculator__slider')]/div")
  private val takeLoanButton: By = By.cssSelector("[data-test-id='calculator_submit']")
  private val creditAmountIdentification: String = "amount"
  private val creditPeriodIdentification: String = "days"

  fun getActualCreditAmountValue(): String? {
    return getInputValue(calculatorAmount)
  }

  fun getActualCreditPeriodValue(): String? {
    return getInputValue(calculatorPeriod)
  }

  fun setCreditAmountSlider(xOffset: Int, yOffset: Int) {
    setValueUsingSlider(creditAmountSliderPoint, xOffset, yOffset)
  }

  fun setCreditPeriodSlider(xOffset: Int, yOffset: Int) {
    setValueUsingSlider(creditPeriodSliderPoint, xOffset, yOffset)
  }

  fun setCreditAmountSliderJS(xOffsetMin: Double, xOffsetMax: Double) {
    setValueUsingSliderJS(creditAmountSliderPoint, creditAmountSliderLine, xOffsetMin, xOffsetMax)
  }

  fun setCreditPeriodSliderJS(xOffsetMin: Double, xOffsetMax: Double) {
    setValueUsingSliderJS(creditPeriodSliderPoint, creditPeriodSliderLine, xOffsetMin, xOffsetMax)
  }

  fun setCreditValuesJS(amount: String, period: String) {
    setInputValueJS(creditAmountIdentification, amount)
    setInputValueJS(creditPeriodIdentification, period)
  }

  fun clickTakeLoanButton() {
    clickButton(takeLoanButton)
  }

  fun clickTakeLoanButtonJS() {
    clickButtonJS(takeLoanButton)
  }
}