package elements

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By
import utils.MmTestException

object Button {
  fun isButtonEnabled(element: By) {
    `$`(element).should(Condition.enabled)
  }

  fun isButtonDisplayed(element: By): Boolean {
    return `$`(element).isDisplayed
  }

  fun clickButton(element: By) {
    if (`$`(element).isDisplayed) {
      `$`(element).click()
    } else throw MmTestException("The button $element is not displayed")
  }

  fun clickButtonJS(element: By) {
    Selenide.executeJavaScript<Any>("arguments[0].click();", `$`(element))
  }
}