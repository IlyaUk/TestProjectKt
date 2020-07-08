package services

import config.ApplicationConfig
import pages.PrivateAreaHomePage
import pages.PrivateAreaLoginPage

class PrivateAreaOperations(config: ApplicationConfig) : BasePageOperations() {
  private val privateAreaLoginPage = PrivateAreaLoginPage()
  private val privateAreaHomePage = PrivateAreaHomePage()

  override val pageUrlEndpoint: String = config.privateAreaServiceEndpoint

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