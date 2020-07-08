package elements

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.By
import utils.MmTestException

object Button {
  private val log: Logger = LogManager.getLogger("Logger")
  fun isButtonEnabled(element: By) {
    `$`(element).shouldBe(Condition.enabled)
  }

  fun isButtonDisplayed(element: By): Boolean {
    return `$`(element).isDisplayed
  }

  fun clickButton(element: By) {
    log.info("The button has been clicked")
    `$`(element).also {
      if (it.isDisplayed){
        it.click()
      } else throw MmTestException("The button $element is not displayed")
    }
  }

  fun clickButtonJS(element: By) {
    Selenide.executeJavaScript<Any>("arguments[0].click();", `$`(element))
  }
}