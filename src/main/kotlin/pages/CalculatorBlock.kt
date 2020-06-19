package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions

class CalculatorBlock {
    private val calculatorAmount: By = By.cssSelector("[data-test-id='calculator_amount']")
    private val calculatorPeriod: By = By.cssSelector("[data-test-id='calculator_days']")
    val creditAmountSlider: By = By.xpath("//*[@data-test-id='amount']//div[contains(@class, 'mainCalculator__slider')]/span")
    private val creditPeriodSlider: By = By.xpath("//*[@data-test-id='period']//div[contains(@class, 'mainCalculator__slider')]/span")
    private val takeLoanButton: By = By.cssSelector("[data-test-id='calculator_submit']")

    fun checkCalculatorAmountValue(driver: WebDriver): String? {
        return driver.findElement(calculatorAmount).getAttribute("value")
    }

    fun checkCalculatorPeriodValue(driver: WebDriver): String? {
        return driver.findElement(calculatorPeriod).getAttribute("value")
    }

    fun moveCreditAmountSlider(driver: WebDriver, xOffset: Int, yOffset: Int) {
        val actions = Actions(driver)
        actions.dragAndDropBy(driver.findElement(creditAmountSlider), xOffset, yOffset).perform()
        actions.release(driver.findElement(creditAmountSlider))
    }

    fun moveCreditPeriodSlider(driver: WebDriver, xOffset: Int, yOffset: Int) {
        val actions = Actions(driver)
        actions.dragAndDropBy(driver.findElement(creditPeriodSlider), xOffset, yOffset).perform()
        actions.release(driver.findElement(creditPeriodSlider))
    }

    fun checkTakeLoanButtonAvailable(driver: WebDriver): Boolean {
        return driver.findElement(takeLoanButton).isEnabled
    }
}