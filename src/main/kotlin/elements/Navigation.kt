package elements

import org.openqa.selenium.WebDriver

object Navigation {

  private fun composeURLFromConfigFile(username: String, password: Int, host: String, endpoint: String): String {
    return "https://$username:$password@$host/$endpoint"
  }

  fun open(driver: WebDriver, username: String, password: Int, host: String, endpoint: String = "") {
    val url = composeURLFromConfigFile(username, password, host, endpoint)
    driver.get(url)
  }

  fun close(driver: WebDriver) {
    driver.quit()
  }
}
