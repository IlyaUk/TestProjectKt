package pages

import org.openqa.selenium.By

class CalculatorBlock {
  val calculatorAmount: By = By.cssSelector("[data-test-id='calculator_amount']")
  val calculatorPeriod: By = By.cssSelector("[data-test-id='calculator_days']")
  val creditAmountSliderPoint: By = By.xpath("//*[@data-test-id='amount']//div[contains(@class, 'mainCalculator__slider')]/span")
  val creditPeriodSliderPoint: By = By.xpath("//*[@data-test-id='period']//div[contains(@class, 'mainCalculator__slider')]/span")
  val creditAmountSliderLine: By = By.xpath("//*[@data-test-id='amount']//div[contains(@class, 'mainCalculator__slider')]/div")
  val creditPeriodSliderLine: By = By.xpath("//*[@data-test-id='period']//div[contains(@class, 'mainCalculator__slider')]/div")
  val takeLoanButton: By = By.cssSelector("[data-test-id='calculator_submit']")
}