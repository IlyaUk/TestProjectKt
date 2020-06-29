package privatearea

import config.ConfigSource
import config.ConfigurationProvider
import driver.WebDriverManager
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
  private lateinit var privateAreaLoginPage: PrivateAreaLoginPage
  private lateinit var driver: WebDriver
  private val user = "u"
  private val pass = "0"

  @BeforeEach
  fun getWebDriver() {
    driver = WebDriverManager().getDriver()
  }

  @Test
  fun `login to PA`() {
    privateAreaLoginPage = PrivateAreaLoginPage(driver)

    privateAreaLoginPage.open(username, password, host, endpoint)
    privateAreaLoginPage.login(user, pass)
    privateAreaLoginPage.clickLoginButton()

    val privateAreaHomePage = PrivateAreaHomePage(driver)
    Assertions.assertTrue(privateAreaHomePage.isOnHomePage())

    privateAreaLoginPage.close()
  }
}