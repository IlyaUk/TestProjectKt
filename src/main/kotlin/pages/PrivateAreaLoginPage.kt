package pages

import elements.Button.clickButton
import elements.Input.inputValue
import org.openqa.selenium.By

class PrivateAreaLoginPage {
  private val loginInput: By = By.cssSelector("input[name='login']")
  private val passInput: By = By.cssSelector("input[name='password']")
  private val loginButton: By = By.cssSelector("button[name='login-btn']")

  fun inputLogin(user: String) {
    inputValue(loginInput, user)
  }

  fun inputPassword(pass: String) {
    inputValue(passInput, pass)
  }

  fun clickLoginButton() {
    clickButton(loginButton)
  }
}
