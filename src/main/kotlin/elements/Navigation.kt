package elements

import org.openqa.selenium.WebDriver

object Navigation {

  fun open(driver: WebDriver, url: String) {
    driver.get(url)
  }

  fun close(driver: WebDriver) {
    driver.quit()
  }
}
