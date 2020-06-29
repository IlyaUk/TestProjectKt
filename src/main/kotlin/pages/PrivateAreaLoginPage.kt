package pages

import elements.Button.clickButton
import elements.Input.inputValue
import elements.Navigation.close
import elements.Navigation.open
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

class PrivateAreaLoginPage(private val driver: WebDriver) {
  private val loginInput: By = By.cssSelector("input[name='login']")
  private val passInput: By = By.cssSelector("input[name='password']")
  private val loginButton: By = By.cssSelector("button[name='login-btn']")

  fun open(username: String, password: Int, host: String, endpoint: String) {
    open(driver, username, password, host, endpoint)
  }

  fun login(user: String, pass: String) {
    inputLogin(user)
    inputPassword(pass)
    clickLoginButton()
  }

  private fun inputLogin(user: String) {
    inputValue(driver, user, loginInput)
  }

  private fun inputPassword(pass: String) {
    inputValue(driver, pass, passInput)
  }

  fun clickLoginButton() {
    clickButton(driver, loginButton)
  }

  fun close() {
    close(driver)
  }
}
