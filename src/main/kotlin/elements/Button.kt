package elements

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.By

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
    log.info("Click button $element")
    `$`(element).shouldBe(Condition.visible).click()
  }

  fun clickButtonJS(element: By) {
    log.info("Click button $element with JS")
    `$`(element).shouldBe(Condition.visible)
    Selenide.executeJavaScript<Any>("arguments[0].click();", `$`(element))
  }
}