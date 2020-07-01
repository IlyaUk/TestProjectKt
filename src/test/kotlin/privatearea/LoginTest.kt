package privatearea

import BaseUiTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import services.PrivateAreaOperations
import utils.Waiter

class LoginTest : BaseUiTest() {
  private val user = "u"
  private val pass = "p"

  @Test
  fun `login to PA`() {
    PrivateAreaOperations(configObject).apply {
      openLoginPage()
      Waiter().waitDomModelLoad(5)
      login(user, pass)
      Assertions.assertTrue(isOnPrivateAreaHomePage())
    }
  }
}