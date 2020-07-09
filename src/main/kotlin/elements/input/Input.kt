package elements.input

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.By
import utils.MmTestException

object Input {
  private const val loggerName = "Input wrapper"
  private val log: Logger = LogManager.getLogger(loggerName)

  fun getInputValue(element: By): String? {
    log.info("Retrieve input value for $element")
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
    if (!isTextInputMatchExpected(element, value)) {
      val exception = MmTestException("Incorrect input in field $element")
      log.error(exception)
      throw exception
    }
  }

  private fun isTextInputMatchExpected(actualInput: By, expectedValue: String): Boolean {
    val actualInputValue = getInputValue(actualInput).toString()
    return actualInputValue == expectedValue
  }
}