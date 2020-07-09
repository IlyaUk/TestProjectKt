package services

import config.ApplicationConfig
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import pages.PrivateAreaHomePage
import pages.PrivateAreaLoginPage

class PrivateAreaOperations(config: ApplicationConfig) : BasePageOperations() {
  private val privateAreaLoginPage = PrivateAreaLoginPage()
  private val privateAreaHomePage = PrivateAreaHomePage()
  private val loggerName = PrivateAreaOperations::class.simpleName
  private val log: Logger = LogManager.getLogger(loggerName)

  override val pageUrlEndpoint: String = config.privateAreaServiceEndpoint

  fun login(user: String, pass: String) {
    log.info("Log in to PA")
    privateAreaLoginPage.apply {
      inputLogin(user)
      inputPassword(pass)
      clickLoginButton()
    }
  }

  fun isOnPrivateAreaHomePage(): Boolean {
    log.info("Navigate to PA home page")
    return privateAreaHomePage.isOnHomePage()
  }
}