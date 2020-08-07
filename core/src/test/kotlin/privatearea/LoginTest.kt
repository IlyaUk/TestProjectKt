package privatearea

import BaseUiTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import services.PrivateAreaOperations
@Disabled
class LoginTest : BaseUiTest() {
  private val user = "u"
  private val pass = "p"

  @Test
  fun `login to PA`() {
    PrivateAreaOperations(config).apply {
      openPage()
      login(user, pass)
      Assertions.assertTrue(isOnPrivateAreaHomePage())
    }
  }
}