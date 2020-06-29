package pages

import elements.Button.isButtonAvailable
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import utils.Waiter

class PrivateAreaHomePage(private val driver: WebDriver) {
  private val signOutButton: By = By.cssSelector("a[data-test-id='logOut']")

  fun isOnHomePage(): Boolean {
    Waiter().waitFluentlyForElement(driver, signOutButton)
    return isButtonAvailable(driver, signOutButton)
  }
}
