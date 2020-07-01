package utils

import driver.WebDriverConfigProvider
import driver.WebDriverManager
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class Waiter {
  private val defaultTimeOut: Long = WebDriverConfigProvider().getDriverConfig().defaultTimeoutSec
  private val defaultPollingInterval: Long = 500

  fun waitExplicitlyForElement(element: By) {
    WebDriverWait(WebDriverManager.getDriver(), defaultTimeOut).until(
        ExpectedConditions.visibilityOfAllElementsLocatedBy(element))
  }

  fun waitFluentlyForElement(element: By) {
    FluentWait(WebDriverManager.getDriver())
        .withTimeout(Duration.ofSeconds(defaultTimeOut))
        .pollingEvery(Duration.ofMillis(defaultPollingInterval))
        .ignoring(WebDriverException::class.java)
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element))
  }

  fun waitForJQuery() {
    WebDriverWait(WebDriverManager.getDriver(), defaultTimeOut).until { webDriver ->
      val js = webDriver as JavascriptExecutor
      js.executeScript("return JQuery.active === 0") as Boolean
    }
  }

  fun waitDomModelLoad(timeoutInSeconds: Long) {
    var domPreviousElementsCount: Long = 0
    WebDriverWait(WebDriverManager.getDriver(), timeoutInSeconds)
        .ignoring(WebDriverException::class.java)
        .until { webDriver ->
          val js = webDriver as JavascriptExecutor
          fun isPageDomModelFullyLoaded(): Boolean {
            val domActualElementsCount: Long = js.executeScript(
                "return document.getElementsByTagName('*').length") as Long
            return if (domPreviousElementsCount == domActualElementsCount && isPageHasDocumentReadyState(timeoutInSeconds)) {
              true
            } else {
              domPreviousElementsCount = domActualElementsCount
              false
            }
          }
          isPageDomModelFullyLoaded()
        }
  }

  fun waitElementNotExistsWithTimeout(elementName: String) {
    var elementExists = false
    WebDriverWait(WebDriverManager.getDriver(), defaultTimeOut)
        .ignoring(WebDriverException::class.java)
        .until { webDriver ->
          val js = webDriver as JavascriptExecutor
          fun isElementExistsInDom(): Boolean {
            if (js.executeScript("return !!document.getElementsByName('$elementName').length") as Boolean) {
              elementExists = true
            }
            return elementExists
          }
          isElementExistsInDom()
        }
  }

  private fun isPageHasDocumentReadyState(timeout: Long): Boolean {
    var pageState = false
    WebDriverWait(WebDriverManager.getDriver(), timeout).until { webDriver ->
      val js = webDriver as JavascriptExecutor
      if (js.executeScript("return document.readyState") == "complete") {
        pageState = true
      }
    }
    return pageState
  }
}