package pages

import elements.Button.isButtonEnabled
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import utils.Waiter

class PrivateAreaHomePage(private val driver: WebDriver) {
  private val signOutButton: By = By.cssSelector("[data-test-id='logOut']")

  fun isOnHomePage(): Boolean {
    Waiter().waitFluentlyForElement(driver, signOutButton)
    return isButtonEnabled(driver, signOutButton)
  }
}
