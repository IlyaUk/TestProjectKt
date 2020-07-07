package utils

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import driver.WebDriverConfigProvider
import driver.WebDriverManager.Companion.getDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.WebDriverWait
import utils.JavaScriptOperations.getJsExecutor
import java.time.Duration

class Waiter {
  private val defaultTimeoutMilliseconds: Long = WebDriverConfigProvider().getDriverConfig().defaultTimeoutMilliseconds
  private val defaultPollingInterval: Long = 500

  fun waitElementVisibleWithTimeout(element: By, timeoutSeconds: Long = defaultTimeoutMilliseconds) {
    Selenide.`$`(element).waitUntil(Condition.visible, timeoutSeconds)
  }

  fun waitElementEnabledWithTimeout(element: By, timeoutSeconds: Long = defaultTimeoutMilliseconds) {
    Selenide.`$`(element).waitUntil(Condition.enabled, timeoutSeconds)
  }

  fun waitExplicitlyForElement(element: By) {
    WebDriverWait(getDriver(), defaultTimeoutMilliseconds).until(
        ExpectedConditions.visibilityOfAllElementsLocatedBy(element))
  }

  fun waitFluentlyForElement(element: By) {
    FluentWait(getDriver())
        .withTimeout(Duration.ofSeconds(defaultTimeoutMilliseconds))
        .pollingEvery(Duration.ofMillis(defaultPollingInterval))
        .ignoring(WebDriverException::class.java)
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element))
  }

  fun waitForJQuery() {
    WebDriverWait(getDriver(), defaultTimeoutMilliseconds).until { webDriver ->
      getJsExecutor().executeScript("return JQuery.active === 0") as Boolean
    }
  }

  fun waitDomModelLoad(timeoutInSeconds: Long) {
    var domPreviousElementsCount: Long = 0
    WebDriverWait(WebDriverRunner.getWebDriver(), timeoutInSeconds)
        .ignoring(WebDriverException::class.java)
        .until {
          fun isPageDomModelFullyLoaded(): Boolean {
            val domActualElementsCount: Long = getJsExecutor().executeScript(
                "return document.getElementsByTagName('*').length") as Long
            return if (domPreviousElementsCount == domActualElementsCount && isPageHasDocumentReadyState(
                    timeoutInSeconds)) {
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
    WebDriverWait(WebDriverRunner.getWebDriver(), defaultTimeoutMilliseconds)
        .ignoring(WebDriverException::class.java)
        .until { webDriver ->
          fun isElementExistsInDom(): Boolean {
            if (getJsExecutor().executeScript(
                    "return !!document.getElementsByName('$elementName').length") as Boolean) {
              elementExists = true
            }
            return elementExists
          }
          isElementExistsInDom()
        }
  }

  private fun isPageHasDocumentReadyState(timeout: Long): Boolean {
    var pageState = false
    WebDriverWait(WebDriverRunner.getWebDriver(), timeout).until { webDriver ->
      if (getJsExecutor().executeScript("return document.readyState") == "complete") {
        pageState = true
      }
    }
    return pageState
  }
}