package elements

import com.codeborne.selenide.Selenide

object Navigation {

  fun open(url: String) {
    Selenide.open(url)
  }

  fun close() {
    Selenide.closeWebDriver()
  }
}