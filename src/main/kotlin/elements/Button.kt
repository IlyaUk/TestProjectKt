package elements

import driver.WebDriverManager.Companion.getDriver
import org.openqa.selenium.By
import utils.MmTestException
import utils.JavaScriptOperations.getJsExecutor

object Button {
  fun isButtonEnabled(element: By): Boolean {
    return getDriver().findElement(element).isEnabled
  }

  fun isButtonDisplayed(element: By): Boolean {
    return getDriver().findElement(element).isDisplayed
  }

  fun isButtonExists(element: By): Boolean {
    return getDriver().findElements(element).size > 0
  }

  fun clickButton(element: By) {
    if (isButtonExists(element)) {
      getDriver().findElement(element).click()
    } else throw MmTestException("The button $element is not displayed")
  }

  fun clickButtonJS(element: By) {
    val driver = getDriver()
    getJsExecutor().executeScript("arguments[0].click();", driver.findElement(element))
  }
}