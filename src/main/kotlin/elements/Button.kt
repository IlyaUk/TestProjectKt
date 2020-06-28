package elements

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

object Button {
  fun isTakeLoanButtonAvailable(driver: WebDriver, element: By): Boolean {
    return driver.findElement(element).isEnabled
  }

  fun clickTakeLoanButton(driver: WebDriver, element: By) {
    driver.findElement(element).click()
  }

  fun clickTakeLoanButtonJS(driver: WebDriver, element: By) {
    val js = driver as JavascriptExecutor
    js.executeScript("arguments[0].click();", driver.findElement(element))
  }
}