package utils

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner.getWebDriver
import driver.config.DriverConfigProvider
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.By
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.support.ui.WebDriverWait

class Waiter {
  private val log: Logger = LogManager.getLogger(Waiter::class.simpleName)
  private val defaultTimeoutMilliseconds: Long = DriverConfigProvider().getSelenideDriverConfig().defaultTimeoutMilliseconds

  fun waitElementVisibleWithTimeout(element: By, timeoutMilliseconds: Long = defaultTimeoutMilliseconds) {
    log.info("Wait visibility of $element")
    Selenide.`$`(element).waitUntil(Condition.visible, timeoutMilliseconds)
  }

  fun waitElementEnabledWithTimeout(element: By, timeoutMilliseconds: Long = defaultTimeoutMilliseconds) {
    log.info("Wait until $element is enabled")
    Selenide.`$`(element).waitUntil(Condition.enabled, timeoutMilliseconds)
  }

  fun waitDomModelLoad(timeoutInSeconds: Long) {
    log.info("Wait for DOM model load")
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