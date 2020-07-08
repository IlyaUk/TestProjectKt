package privatearea

import BaseUiTest
import org.apache.logging.log4j.LogManager
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import services.PrivateAreaOperations

class LoginTest : BaseUiTest() {
  private val user = "test1220406@mail.ru"
  private val pass = "11111111"

  @Test
  fun `login to PA`() {
    PrivateAreaOperations(config).apply {
      openPage()
      login(user, pass)
      Assertions.assertTrue(isOnPrivateAreaHomePage())
    }
  }
}