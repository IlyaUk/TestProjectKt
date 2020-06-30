package pages

import elements.Button.clickButton
import elements.Input.inputValue
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class PrivateAreaLoginPage(private val driver: WebDriver) {
  private val loginInput: By = By.cssSelector("input[name='login']")
  private val passInput: By = By.cssSelector("input[name='password']")
  private val loginButton: By = By.cssSelector("button[name='login-btn']")

  fun inputLogin(user: String) {
    inputValue(driver, loginInput, user)
  }

  fun inputPassword(pass: String) {
    inputValue(driver, passInput, pass)
  }

  fun clickLoginButton() {
    clickButton(driver, loginButton)
  }
}
