package selenide

import BaseUiTest
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.open
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

class SelenideSmokeTest : BaseUiTest() {
  private val user = "u"
  private val pass = "p"

  @Test
  fun `Login to PA with Selenide`() {
    open("https://moneyman:1005@qa-delivery-mx-master.moneyman.ru/private-area/static/#/login/")
    `$`(By.cssSelector("input[name='login']")).value = user
    `$`(By.cssSelector("input[name='password']")).value = pass
    `$`(By.cssSelector("button[name='login-btn']")).click()
  }
}