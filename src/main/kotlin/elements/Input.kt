package elements

import driver.WebDriverManager
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import utils.MMTestException
import utils.Waiter

object Input {

  fun getInputValue(element: By): String? {
    return WebDriverManager.getDriver().findElement(element).getAttribute("value")
  }

  fun setInputValueJS(element: String, value: String) {
    val driver = WebDriverManager.getDriver()
    val js = driver as JavascriptExecutor
    Waiter().waitElementNotExistsWithTimeout(element)
    js.executeScript("document.querySelectorAll('.mainCalculatorDynamic__input[name=$element]')[0].value = '$value'")
  }

  fun inputValue(element: By, value: String) {
    val driver = WebDriverManager.getDriver()
    driver.findElement(element).sendKeys(value)
    if (driver.findElement(element).getAttribute("value").toString() != value) {
      throw MMTestException("Incorrect input in field $element")
    }
  }
}