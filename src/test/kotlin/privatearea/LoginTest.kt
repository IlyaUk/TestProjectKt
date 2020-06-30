package privatearea

import BaseUiTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import services.PrivateAreaOperations

class LoginTest : BaseUiTest() {
  private val user = "u"
  private val pass = "p"

  @Test
  fun `login to PA`() {
    PrivateAreaOperations(driver, configObject).apply {
      openLoginPage()
      //TODO("Implement appropriate waiter")
      Thread.sleep(6500)
      login(user, pass)
      Assertions.assertTrue(isOnPrivateAreaHomePage())
    }
  }
}