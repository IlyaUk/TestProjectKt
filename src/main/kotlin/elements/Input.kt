package elements

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

object Input {

  fun getInputValue(driver: WebDriver, element: By): String? {
    return driver.findElement(element).getAttribute("value")
  }

  fun setInputValueJS(driver: WebDriver, element: String, value: String) {
    val js = driver as JavascriptExecutor
    js.executeScript("document.querySelectorAll('.mainCalculatorDynamic__input[name=$element]')[0].value = '$value'")
  }

  fun inputValue(driver: WebDriver, element: By, value: String) {
    driver.findElement(element).sendKeys(value)
  }
}