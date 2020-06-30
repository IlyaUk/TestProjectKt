package pages

import elements.Button.clickButton
import elements.Button.clickButtonJS
import elements.Button.isButtonEnabled
import elements.Input.getInputValue
import elements.Input.setInputValueJS
import elements.Slider.setValueUsingSlider
import elements.Slider.setValueUsingSliderJS
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class CalculatorBlock(private val driver: WebDriver) {
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
    return getInputValue(driver, calculatorAmount)
  }

  fun getActualCreditPeriodValue(): String? {
    return getInputValue(driver, calculatorPeriod)
  }

  fun setCreditAmountSlider(xOffset: Int, yOffset: Int) {
    setValueUsingSlider(driver, creditAmountSliderPoint, xOffset, yOffset)
  }

  fun setCreditPeriodSlider(xOffset: Int, yOffset: Int) {
    setValueUsingSlider(driver, creditPeriodSliderPoint, xOffset, yOffset)
  }

  fun setCreditAmountSliderJS(xOffsetMin: Double, xOffsetMax: Double) {
    setValueUsingSliderJS(driver, creditAmountSliderPoint, creditAmountSliderLine, xOffsetMin, xOffsetMax)
  }

  fun setCreditPeriodSliderJS(xOffsetMin: Double, xOffsetMax: Double) {
    setValueUsingSliderJS(driver, creditPeriodSliderPoint, creditPeriodSliderLine, xOffsetMin, xOffsetMax)
  }

  fun setCreditValuesJS(amount: String, period: String) {
    setInputValueJS(driver, creditAmountIdentification, amount)
    setInputValueJS(driver, creditPeriodIdentification, period)
  }

  fun clickTakeLoanButton() {
    isButtonEnabled(driver, takeLoanButton)
    clickButton(driver, takeLoanButton)
  }

  fun clickTakeLoanButtonJS() {
    isButtonEnabled(driver, takeLoanButton)
    clickButtonJS(driver, takeLoanButton)
  }
}