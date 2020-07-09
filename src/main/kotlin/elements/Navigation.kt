package elements

import com.codeborne.selenide.Selenide
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Navigation {
  private val log: Logger = LogManager.getLogger(Navigation::class.simpleName)

  fun open(url: String) {
    log.info("Open $url")
    Selenide.open(url)
  }

  fun close() {
    log.info("Close the driver")
    Selenide.closeWebDriver()
  }
}