package utils

import driver.selenium.SeleniumDriverManager.Companion.getDriver
import org.openqa.selenium.JavascriptExecutor

object JavaScriptOperations {

  fun getJsExecutor():JavascriptExecutor {
    return getDriver() as JavascriptExecutor
  }
}