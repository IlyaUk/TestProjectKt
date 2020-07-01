package elements

import driver.WebDriverManager

object Navigation {

  fun open(url: String) {
    WebDriverManager.getDriver().get(url)
  }

  fun close() {
    WebDriverManager.getDriver().quit()
  }
}