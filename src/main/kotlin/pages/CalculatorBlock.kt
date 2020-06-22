package pages

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions

class CalculatorBlock {
  val calculatorAmount: By = By.cssSelector("[data-test-id='calculator_amount']")
  val calculatorPeriod: By = By.cssSelector("[data-test-id='calculator_days']")
  val creditAmountSlider: By = By.xpath(
      "//*[@data-test-id='amount']//div[contains(@class, 'mainCalculator__slider')]/span")
  val creditPeriodSlider: By = By.xpath(
      "//*[@data-test-id='period']//div[contains(@class, 'mainCalculator__slider')]/span")
  val takeLoanButton: By = By.cssSelector("[data-test-id='calculator_submit']")

  fun getCalculatorAmountValue(driver: WebDriver): String? {
    return driver.findElement(calculatorAmount).getAttribute("value")
  }

  fun getCalculatorPeriodValue(driver: WebDriver): String? {
    return driver.findElement(calculatorPeriod).getAttribute("value")
  }

  fun setCreditAmountSlider(driver: WebDriver, xOffset: Int, yOffset: Int) {
    val actions = Actions(driver)
    actions.dragAndDropBy(driver.findElement(creditAmountSlider), xOffset, yOffset).perform()
    actions.release(driver.findElement(creditAmountSlider))
  }

  fun setCreditPeriodSlider(driver: WebDriver, xOffset: Int, yOffset: Int) {
    val actions = Actions(driver)
    actions.dragAndDropBy(driver.findElement(creditPeriodSlider), xOffset, yOffset).perform()
    actions.release(driver.findElement(creditPeriodSlider))
  }

  fun isTakeLoanButtonAvailable(driver: WebDriver): Boolean {
    return driver.findElement(takeLoanButton).isEnabled
  }

  fun setCreditAmountSliderJS(driver: WebDriver, xOffsetMin: Double, xOffsetMax: Double) {
    val js = driver as JavascriptExecutor
    js.executeScript("arguments[0].setAttribute('style', 'left: $xOffsetMin%')", driver.findElement(By.xpath
    ("//*[@data-test-id='amount']//div[contains(@class, 'mainCalculator__slider')]/span")))
    js.executeScript("arguments[0].setAttribute('style', 'width: $xOffsetMin%')", driver.findElement(By.xpath
    ("//*[@data-test-id='amount']//div[contains(@class, 'mainCalculator__slider')]/div")))

    js.executeScript("arguments[0].setAttribute('style', 'left: $xOffsetMax%')", driver.findElement(By.xpath
    ("//*[@data-test-id='amount']//div[contains(@class, 'mainCalculator__slider')]/span")))
    js.executeScript("arguments[0].setAttribute('style', 'width: $xOffsetMax%')", driver.findElement(By.xpath
    ("//*[@data-test-id='amount']//div[contains(@class, 'mainCalculator__slider')]/div")))
  }

  fun setCreditPeriodSliderJS(driver: WebDriver, xOffsetMin: Double, xOffsetMax: Double) {
    val js = driver as JavascriptExecutor
    js.executeScript("arguments[0].setAttribute('style', 'left: $xOffsetMin%')", driver.findElement(By.xpath
    ("//*[@data-test-id='period']//div[contains(@class, 'mainCalculator__slider')]/span")))
    js.executeScript("arguments[0].setAttribute('style', 'width: $xOffsetMin%')", driver.findElement(By.xpath
    ("//*[@data-test-id='period']//div[contains(@class, 'mainCalculator__slider')]/div")))

    js.executeScript("arguments[0].setAttribute('style', 'left: $xOffsetMax%')", driver.findElement(By.xpath
    ("//*[@data-test-id='period']//div[contains(@class, 'mainCalculator__slider')]/span")))
    js.executeScript("arguments[0].setAttribute('style', 'width: $xOffsetMax%')", driver.findElement(By.xpath
    ("//*[@data-test-id='period']//div[contains(@class, 'mainCalculator__slider')]/div")))
  }

  fun setCreditAmountJS(driver: WebDriver, amount: String) {
    val js = driver as JavascriptExecutor
    js.executeScript("document.querySelectorAll('.mainCalculatorDynamic__input')[0].value = '$amount'")
  }

  fun setCreditPeriodJS(driver: WebDriver, period: String) {
    val js = driver as JavascriptExecutor
    js.executeScript("document.querySelectorAll('.mainCalculatorDynamic__input')[1].value = '$period'")
  }

  fun clickTakeLoanButtonJS(driver: WebDriver) {
    val js = driver as JavascriptExecutor
    js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("[data-test-id='calculator_submit']")))
  }
}