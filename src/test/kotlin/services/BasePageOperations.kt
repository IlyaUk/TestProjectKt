package services

import elements.Navigation.open
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import utils.Waiter

abstract class BasePageOperations {
  abstract val pageUrlEndpoint: String
  open val defaultDomTimeout: Long = 10
  private val log: Logger = LogManager.getLogger(BasePageOperations::class.simpleName)

  fun openPage() {
    open(pageUrlEndpoint)
    Waiter().waitDomModelLoad(defaultDomTimeout)
  }
}