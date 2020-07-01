package elements

import driver.WebDriverManager.Companion.getDriver

object Navigation {

  fun open(url: String) {
    getDriver().get(url)
  }

  fun close() {
    getDriver().quit()
  }
}