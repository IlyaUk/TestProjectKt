package elements

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.By
import utils.MmTestException

object Button {
  private const val loggerName = "Button wrapper"
  private val log: Logger = LogManager.getLogger(loggerName)

  fun isButtonEnabled(element: By): Boolean {
    log.info("Check button $element is enabled")
    return `$`(element).isEnabled
  }

  fun isButtonDisplayed(element: By): Boolean {
    log.info("Check button $element is displayed")
    return `$`(element).isDisplayed
  }

  fun clickButton(element: By) {
    `$`(element).also {
      if (it.isDisplayed) {
        log.info("The button $element is clicked")
        it.click()
      } else {
        val exception = MmTestException("The button $element is not displayed")
        log.error(exception)
        throw exception
      }
    }
  }

  fun clickButtonJS(element: By) {
    log.info("The button $element is clicked with JS")
    Selenide.executeJavaScript<Any>("arguments[0].click();", `$`(element))
  }
}