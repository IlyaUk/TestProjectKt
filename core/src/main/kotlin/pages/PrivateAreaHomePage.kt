package pages

import elements.Button.isButtonDisplayed
import org.openqa.selenium.By
import utils.Waiter

class PrivateAreaHomePage {
  private val signOutButton: By = By.cssSelector("[data-test-id='logOut']")

  fun isOnHomePage(): Boolean {
    Waiter().waitElementVisibleWithTimeout(signOutButton)
    return isButtonDisplayed(signOutButton)
  }
}
