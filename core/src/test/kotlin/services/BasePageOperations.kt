package services

import elements.Navigation.open
import utils.Waiter

abstract class BasePageOperations {
  abstract val pageUrlEndpoint: String
  open val defaultDomTimeout: Long = 10

  fun openPage() {
    open(pageUrlEndpoint)
    Waiter().waitDomModelLoad(defaultDomTimeout)
  }
}