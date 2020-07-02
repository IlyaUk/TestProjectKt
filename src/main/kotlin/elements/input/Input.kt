package elements.input

import driver.WebDriverManager.Companion.getDriver
import org.openqa.selenium.By
import utils.JavaScriptOperations.getJsExecutor
import utils.MmTestException
import utils.Waiter

object Input {

  fun getInputValue(element: By): String? {
    return getDriver().findElement(element).getAttribute("value")
  }

  fun setInputValueJS(element: String, value: String) {
    Waiter().waitElementNotExistsWithTimeout(element)
    getJsExecutor().executeScript("document.querySelectorAll('.mainCalculatorDynamic__input[name=$element]')[0].value = '$value'")
  }

  fun inputValue(element: By, value: String) {
    val driver = getDriver()
    driver.findElement(element).sendKeys(value)
    if (!isTextInputMatchExpected(element, value)) {
      throw MmTestException("Incorrect input in field $element")
    }
  }

  private fun isTextInputMatchExpected(actualInput: By, expectedValue: String): Boolean {
    val actualInputValue = getInputValue(actualInput).toString()
    return actualInputValue == expectedValue
  }
}