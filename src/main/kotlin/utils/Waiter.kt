package utils

import driver.WebDriverConfigProvider
import driver.WebDriverManager.Companion.getDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import utils.JavaScriptOperations.getJsExecutor

class Waiter {
  private val defaultTimeOut: Long = WebDriverConfigProvider().getDriverConfig().defaultTimeoutSec
  private val defaultPollingInterval: Long = 500

  fun waitExplicitlyForElement(element: By) {
    WebDriverWait(getDriver(), defaultTimeOut).until(
        ExpectedConditions.visibilityOfAllElementsLocatedBy(element))
  }

  fun waitFluentlyForElement(element: By) {
    FluentWait(getDriver())
        .withTimeout(Duration.ofSeconds(defaultTimeOut))
        .pollingEvery(Duration.ofMillis(defaultPollingInterval))
        .ignoring(WebDriverException::class.java)
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element))
  }

  fun waitForJQuery() {
    WebDriverWait(getDriver(), defaultTimeOut).until { webDriver ->
      getJsExecutor().executeScript("return JQuery.active === 0") as Boolean
    }
  }

  fun waitDomModelLoad(timeoutInSeconds: Long) {
    var domPreviousElementsCount: Long = 0
    WebDriverWait(getDriver(), timeoutInSeconds)
        .ignoring(WebDriverException::class.java)
        .until { webDriver ->
          fun isPageDomModelFullyLoaded(): Boolean {
            val domActualElementsCount: Long = getJsExecutor().executeScript(
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
    WebDriverWait(getDriver(), defaultTimeOut)
        .ignoring(WebDriverException::class.java)
        .until { webDriver ->
          fun isElementExistsInDom(): Boolean {
            if (getJsExecutor().executeScript("return !!document.getElementsByName('$elementName').length") as Boolean) {
              elementExists = true
            }
            return elementExists
          }
          isElementExistsInDom()
        }
  }

  private fun isPageHasDocumentReadyState(timeout: Long): Boolean {
    var pageState = false
    WebDriverWait(getDriver(), timeout).until { webDriver ->
      if (getJsExecutor().executeScript("return document.readyState") == "complete") {
        pageState = true
      }
    }
    return pageState
  }
}