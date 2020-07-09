package elements

import com.codeborne.selenide.Selenide
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Navigation {
  private const val loggerName = "Navigation wrapper"
  private val log: Logger = LogManager.getLogger(loggerName)

  fun open(url: String) {
    log.info("$url is opened")
    Selenide.open(url)
  }

  fun close() {
    log.info("The instance of WebDriver is closed")
    Selenide.closeWebDriver()
  }
}