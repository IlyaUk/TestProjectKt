package services

import config.ApplicationConfig
import pages.PrivateAreaHomePage
import pages.PrivateAreaLoginPage
import reporting.AllureOperations

class PrivateAreaOperations(config: ApplicationConfig) : BasePageOperations() {
  private val privateAreaLoginPage = PrivateAreaLoginPage()
  private val privateAreaHomePage = PrivateAreaHomePage()

  override val pageUrlEndpoint: String = config.privateAreaServiceEndpoint

  fun login(user: String, pass: String) {
    AllureOperations().addStepToReport("[Step][PrivateAreaOperations] Log in to PA")
    privateAreaLoginPage.apply {
      inputLogin(user)
      inputPassword(pass)
      clickLoginButton()
    }
  }

  fun isOnPrivateAreaHomePage(): Boolean {
    AllureOperations().addStepToReport("[Step][PrivateAreaOperations] Navigate to PA home page")
    return privateAreaHomePage.isOnHomePage()
  }
}