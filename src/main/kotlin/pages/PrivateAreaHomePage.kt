package pages

import elements.Button.isButtonEnabled
import org.openqa.selenium.By
import utils.Waiter

class PrivateAreaHomePage {
  private val signOutButton: By = By.cssSelector("[data-test-id='logOut']")

  fun isOnHomePage(): Boolean {
    Waiter().waitFluentlyForElement(signOutButton)
    return isButtonEnabled(signOutButton)
  }
}
