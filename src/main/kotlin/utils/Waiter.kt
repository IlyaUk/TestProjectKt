package utils

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner.getWebDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import driver.config.DriverConfigProvider

class Waiter {
  private val defaultTimeoutMilliseconds: Long = DriverConfigProvider().getSelenideDriverConfig().defaultTimeoutMilliseconds
  private val defaultPollingInterval: Long = 500

  fun waitElementVisibleWithTimeout(element: By, timeoutMilliseconds: Long = defaultTimeoutMilliseconds) {
    Selenide.`$`(element).waitUntil(Condition.visible, timeoutMilliseconds)
  }

  fun waitElementEnabledWithTimeout(element: By, timeoutMilliseconds: Long = defaultTimeoutMilliseconds) {
    Selenide.`$`(element).waitUntil(Condition.enabled, timeoutMilliseconds)
  }

  fun waitExplicitlyForElement(element: By) {
    WebDriverWait(getWebDriver(), defaultTimeoutMilliseconds).until(
        ExpectedConditions.visibilityOfAllElementsLocatedBy(element))
  }

  fun waitFluentlyForElement(element: By) {
    FluentWait(getWebDriver())
        .withTimeout(Duration.ofSeconds(defaultTimeoutMilliseconds))
        .pollingEvery(Duration.ofMillis(defaultPollingInterval))
        .ignoring(WebDriverException::class.java)
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element))
  }

  fun waitForJQuery() {
    WebDriverWait(getWebDriver(), defaultTimeoutMilliseconds).until { webDriver ->
      Selenide.executeJavaScript<Any>("return JQuery.active === 0") as Boolean
    }
  }

  fun waitDomModelLoad(timeoutInSeconds: Long) {
    var domPreviousElementsCount: Long = 0
    WebDriverWait(getWebDriver(), timeoutInSeconds)
        .ignoring(WebDriverException::class.java)
        .until {
          fun isPageDomModelFullyLoaded(): Boolean {
            val domActualElementsCount: Long = Selenide.executeJavaScript<Any>(
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
    WebDriverWait(getWebDriver(), defaultTimeoutMilliseconds)
        .ignoring(WebDriverException::class.java)
        .until { webDriver ->
          fun isElementExistsInDom(): Boolean {
            if (Selenide.executeJavaScript<Any>(
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
    WebDriverWait(getWebDriver(), timeout).until { webDriver ->
      if (Selenide.executeJavaScript<Any>("return document.readyState") == "complete") {
        pageState = true
      }
    }
    return pageState
  }
}