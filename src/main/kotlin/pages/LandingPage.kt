package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions

class LandingPage {
     open class CalculatorBlock {
         private val calculatorAmount:By = By.cssSelector("[data-test-id='calculator_amount']")
         private val calculatorPeriod:By = By.cssSelector("[data-test-id='calculator_days']")
         private val creditAmountSlider: By = By.xpath("//*[@class='mainCalculator__slider mainCalculator__sum ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content']/span[@class='ui-slider-handle ui-corner-all ui-state-default']")
         private val creditPeriodSlider: By = By.xpath("//*[@class='mainCalculator__slider mainCalculator__date ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content']/span[@class='ui-slider-handle ui-corner-all ui-state-default']")
         private val takeLoanButton: By = By.cssSelector("[data-test-id='calculator_submit']")

         fun checkCalculatorAmountValue(driver: WebDriver): String? {
            return driver.findElement(calculatorAmount).getAttribute("value")
        }

        fun checkCalculatorPeriodValue(driver: WebDriver): String? {
            return driver.findElement(calculatorPeriod).getAttribute("value")
        }

        fun moveCreditAmountSlider(driver: WebDriver, xOffset:Int, yOffset:Int) {
            val actions = Actions(driver)
            actions.dragAndDropBy(driver.findElement(creditAmountSlider), xOffset, yOffset).perform()
            //actions.release(driver.findElement(creditAmountSlider))
        }

        fun moveCreditPeriodSlider(driver: WebDriver, xOffset:Int, yOffset:Int) {
            val actions = Actions(driver)
            actions.dragAndDropBy(driver.findElement(creditPeriodSlider), xOffset, yOffset).perform()
            //actions.release(driver.findElement(creditPeriodSlider))
        }

         fun checkTakeLoanButtonAvailable(driver: WebDriver): Boolean {
             return driver.findElement(takeLoanButton).isEnabled
         }
    }
}