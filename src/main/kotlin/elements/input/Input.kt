package elements.input

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By
import utils.MmTestException

object Input {

  fun getInputValue(element: By): String? {
    return `$`(element).getAttribute("value")
  }

  fun setInputValueJS(element: String, value: String) {
    Selenide.executeJavaScript<Any>(
        "document.querySelectorAll('.mainCalculatorDynamic__input[name=$element]')[0].value = '$value'")
  }

  fun inputValue(element: By, value: String) {
    `$`(element).value = value
    if (!isTextInputMatchExpected(element, value)) {
      throw MmTestException("Incorrect input in field $element")
    }
  }

  private fun isTextInputMatchExpected(actualInput: By, expectedValue: String): Boolean {
    val actualInputValue = getInputValue(actualInput).toString()
    return actualInputValue == expectedValue
  }
}