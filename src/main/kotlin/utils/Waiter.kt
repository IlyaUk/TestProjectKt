package utils

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class Waiter {
  private val defaultTimeOut: Long = 10
  private val defaultPollingInterval: Long = 500
  fun waitExplicitlyForElement(driver: WebDriver, element: By) {
    WebDriverWait(driver, defaultTimeOut).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element))
  }

  fun waitFluentlyForElement(driver: WebDriver, element: By) {
    FluentWait(driver)
        .withTimeout(Duration.ofSeconds(defaultTimeOut))
        .pollingEvery(Duration.ofMillis(defaultPollingInterval))
        .ignoring(WebDriverException::class.java)
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element))
  }
}