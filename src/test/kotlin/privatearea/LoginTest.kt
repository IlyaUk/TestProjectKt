package privatearea

import BaseUiTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import reporting.TestListener
import services.PrivateAreaOperations

@ExtendWith(TestListener::class)
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