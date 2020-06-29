package pages

import elements.Button.clickButton
import elements.Button.clickButtonJS
import elements.Button.isButtonAvailable
import elements.Input.getInputValue
import elements.Input.setInputValueJS
import elements.Navigation.close
import elements.Navigation.openPageWithoutEndpoint
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

  fun open(username: String, password: Int, host: String) {
    openPageWithoutEndpoint(driver, username, password, host)
  }

  fun getActualCreditAmountValue(): String? {
    return getInputValue(driver, calculatorAmount)
  }

  fun getActualCreditPeriodValue(): String? {
    return getInputValue(driver, calculatorPeriod)
  }

  fun setCreditAmountSlider(xOffset: Int, yOffset: Int) {
    setValueUsingSlider(driver, xOffset, yOffset, creditAmountSliderPoint)
  }

  fun setCreditPeriodSlider(xOffset: Int, yOffset: Int) {
    setValueUsingSlider(driver, xOffset, yOffset, creditPeriodSliderPoint)
  }

  fun setCreditAmountSliderJS(xOffsetMin: Double, xOffsetMax: Double) {
    setValueUsingSliderJS(driver, xOffsetMin, xOffsetMax, creditAmountSliderPoint, creditAmountSliderLine)
  }

  fun setCreditPeriodSliderJS(xOffsetMin: Double, xOffsetMax: Double) {
    setValueUsingSliderJS(driver, xOffsetMin, xOffsetMax, creditPeriodSliderPoint, creditPeriodSliderLine)
  }

  fun setCreditValuesJS(amount: String, period: String) {
    setInputValueJS(driver, amount, creditAmountIdentification)
    setInputValueJS(driver, period, creditPeriodIdentification)
  }

  fun clickTakeLoanButton() {
    isButtonAvailable(driver, takeLoanButton)
    clickButton(driver, takeLoanButton)
  }

  fun clickTakeLoanButtonJS() {
    isButtonAvailable(driver, takeLoanButton)
    clickButtonJS(driver, takeLoanButton)
  }

  fun close() {
    close(driver)
  }
}