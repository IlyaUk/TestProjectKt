package elements

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.By

object Input {
  private val log: Logger = LogManager.getLogger(Input::class.simpleName)

  fun getInputValue(element: By): String? {
    log.info("Get input value for $element")
    return `$`(element).getAttribute("value")
  }

  fun setInputValueJS(element: String, value: String) {
    log.info("Set input value for $element with JS")
    Selenide.executeJavaScript<Any>(
        "document.querySelectorAll('.mainCalculatorDynamic__input[name=$element]')[0].value = '$value'")
  }

  fun inputValue(element: By, value: String) {
    log.info("Set input value for $element")
    `$`(element).value = value
    `$`(element).shouldBe(Condition.matchesText(value))
  }

  private fun isTextInputMatchExpected(actualInput: By, expectedValue: String): Boolean {
    val actualInputValue = getInputValue(actualInput).toString()
    return actualInputValue == expectedValue
  }
}