package elements

import org.openqa.selenium.WebDriver

object Navigation {

  fun open(driver: WebDriver, username: String, password: Int, host: String, endpoint: String) {
    driver.get("https://$username:$password@$host/$endpoint")
  }

  fun openPageWithoutEndpoint(driver: WebDriver, username: String, password: Int, host: String) {
    driver.get("https://$username:$password@$host/")
  }

  fun close(driver: WebDriver) {
    driver.quit()
  }
}