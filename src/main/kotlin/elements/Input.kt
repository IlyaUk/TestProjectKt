package elements

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

object Input {

  fun getCalculatorAmountValue(driver: WebDriver, element: By): String? {
    return driver.findElement(element).getAttribute("value")
  }

  fun getCalculatorPeriodValue(driver: WebDriver, element: By): String? {
    return driver.findElement(element).getAttribute("value")
  }

  fun setCreditAmountJS(driver: WebDriver, amount: String) {
    val js = driver as JavascriptExecutor
    js.executeScript("document.querySelectorAll('.mainCalculatorDynamic__input')[0].value = '$amount'")
  }

  fun setCreditPeriodJS(driver: WebDriver, period: String) {
    val js = driver as JavascriptExecutor
    js.executeScript("document.querySelectorAll('.mainCalculatorDynamic__input')[1].value = '$period'")
  }
}