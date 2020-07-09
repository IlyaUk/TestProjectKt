package elements

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.By
import utils.MmTestException

object Button {
  private val log: Logger = LogManager.getLogger(Button::class.simpleName)

  fun isButtonEnabled(element: By): Boolean {
    log.info("Verify button $element is enabled")
    return `$`(element).isEnabled
  }

  fun isButtonDisplayed(element: By): Boolean {
    log.info("Verify button $element is displayed")
    return `$`(element).isDisplayed
  }

  fun clickButton(element: By) {
    `$`(element).also {
      if (isButtonDisplayed(element)) {
        log.info("Click button $element")
        it.click()
      } else {
        val exception = MmTestException("The button $element is not displayed")
        log.error(exception.getExceptionMessage())
        throw exception
      }
    }
  }

  fun clickButtonJS(element: By) {
    if (isButtonDisplayed(element)) {
      log.info("Click button $element with JS")
      Selenide.executeJavaScript<Any>("arguments[0].click();", `$`(element))
    } else {
      val exception = MmTestException("The button $element is not displayed")
      log.error(exception.getExceptionMessage())
      throw exception
    }
  }
}