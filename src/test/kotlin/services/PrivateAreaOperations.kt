package services

import config.Configuration
import elements.Navigation.open
import pages.PrivateAreaHomePage
import pages.PrivateAreaLoginPage

class PrivateAreaOperations(private val configObject: Configuration) {
  private val privateAreaLoginPage = PrivateAreaLoginPage()
  private val privateAreaHomePage = PrivateAreaHomePage()
  private var url = configObject.getURLWithAuthorization() + configObject.privateAreaServiceEndpoint

  fun openLoginPage() {
    configObject.apply {
      open(url)
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