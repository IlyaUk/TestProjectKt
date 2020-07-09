package services

import elements.Navigation.open
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import utils.Waiter

abstract class BasePageOperations {
  abstract val pageUrlEndpoint: String
  open val defaultDomTimeout: Long = 10
  private val loggerName = BasePageOperations::class.simpleName
  private val log: Logger = LogManager.getLogger(loggerName)

  fun openPage() {
    log.info("Open $pageUrlEndpoint endpoint and wait for DOM model load")
    open(pageUrlEndpoint)
    Waiter().waitDomModelLoad(defaultDomTimeout)
  }
}