package privatearea

import BaseUiTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import services.PrivateAreaOperations

class LoginTest : BaseUiTest() {
  private val user = "p"
  private val pass = "p"

  @Test
  fun `login to PA`() {
    PrivateAreaOperations(config).apply {
      openLoginPage()
      login(user, pass)
      Assertions.assertTrue(isOnPrivateAreaHomePage())
    }
  }
}