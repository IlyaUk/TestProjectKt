package elements

import driver.WebDriverManager
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import utils.MMTestException

object Button {
  fun isButtonEnabled(element: By): Boolean {
    return WebDriverManager.getDriver().findElement(element).isEnabled
  }

  fun isButtonDisplayed(element: By): Boolean {
    return WebDriverManager.getDriver().findElement(element).isDisplayed
  }

  fun isButtonExists(element: By): Boolean {
    return WebDriverManager.getDriver().findElements(element).size > 0
  }

  fun clickButton(element: By) {
    if (isButtonExists(element)) {
      WebDriverManager.getDriver().findElement(element).click()
    } else throw MMTestException("The button $element is not displayed")
  }

  fun clickButtonJS(element: By) {
    val driver = WebDriverManager.getDriver()
    val js = driver as JavascriptExecutor
    js.executeScript("arguments[0].click();", driver.findElement(element))
  }
}