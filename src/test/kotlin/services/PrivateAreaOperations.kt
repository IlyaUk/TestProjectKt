package services

import config.Configuration
import elements.Navigation.open
import org.openqa.selenium.WebDriver
import pages.PrivateAreaHomePage
import pages.PrivateAreaLoginPage

class PrivateAreaOperations(private val driver: WebDriver, private val configObject: Configuration) {
  private val privateAreaLoginPage = PrivateAreaLoginPage(driver)
  private val privateAreaHomePage = PrivateAreaHomePage(driver)
  private var url = configObject.getURLWithAuthorization() + configObject.privateAreaServiceEndpoint

  fun openLoginPage() {
    configObject.apply {
      open(driver, url)
    }
  }

  fun login(user: String, pass: String) {
    privateAreaLoginPage.apply {
      inputLogin(user)
      inputPassword(pass)
      clickLoginButton()
    }
  }

  fun isOnPrivateAreaHomePage(): Boolean {
    return privateAreaHomePage.isOnHomePage()
  }
}