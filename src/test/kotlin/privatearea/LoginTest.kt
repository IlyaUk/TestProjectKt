package privatearea

import config.ConfigSource
import config.ConfigurationProvider
import driver.WebDriverManager
import elements.Navigation.close
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import pages.PrivateAreaHomePage
import pages.PrivateAreaLoginPage

class LoginTest {
  private val configObject = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig()
  private val username = configObject.user
  private val password = configObject.pass
  private val host = configObject.host
  private val endpoint = configObject.privateAreaServiceEndpoint
  private lateinit var driver: WebDriver
  private val user = "test1220406@mail.ru"
  private val pass = "11111111"

  @BeforeEach
  fun getWebDriver() {
    driver = WebDriverManager().getDriver()
  }

  @AfterEach
  fun quitWebDriver() {
    close(driver)
  }

  @Test
  fun `login to PA`() {
    PrivateAreaLoginPage(driver).apply {
      open(username, password, host, endpoint)
      login(user, pass)
    }
    Assertions.assertTrue(PrivateAreaHomePage(driver).isOnHomePage())
  }
}