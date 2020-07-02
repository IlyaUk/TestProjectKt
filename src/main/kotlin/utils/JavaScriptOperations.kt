package utils

import driver.WebDriverManager.Companion.getDriver
import org.openqa.selenium.JavascriptExecutor

object JavaScriptOperations {

  fun getJsExecutor():JavascriptExecutor {
    return getDriver() as JavascriptExecutor
  }
}