package elements

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

object Input {

  fun getInputValue(driver: WebDriver, element: By): String? {
    return driver.findElement(element).getAttribute("value")
  }

  fun setInputValueJS(driver: WebDriver, value: String, element: String) {
    val js = driver as JavascriptExecutor
    js.executeScript("document.querySelectorAll('.mainCalculatorDynamic__input[name=$element]')[0].value = '$value'")
  }

  fun inputValue(driver: WebDriver, value: String, element: By) {
    driver.findElement(element).sendKeys(value)
  }
}