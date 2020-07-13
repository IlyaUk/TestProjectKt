package reporting

import elements.Navigation.close
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher
import reporting.AllureOperations.attachLogsFile
import reporting.AllureOperations.attachScreenshot
import java.util.*

class TestListener : TestWatcher, AfterEachCallback {
  private val log: Logger = LogManager.getLogger(TestListener::class.simpleName)

  override fun testSuccessful(extensionContext: ExtensionContext) {
    attachLogsFile()
  }

  override fun testDisabled(extensionContext: ExtensionContext, reason: Optional<String>) {
    attachLogsFile()
  }

  override fun testFailed(extensionContext: ExtensionContext, errorCause: Throwable) {
    log.error(getExecutionExceptionFromContext(extensionContext))
    attachLogsFile()
  }

  override fun testAborted(extensionContext: ExtensionContext, errorCause: Throwable) {
    attachLogsFile()
  }

  override fun afterEach(extensionContext: ExtensionContext) {
    if (isTestResultNotSuccessful(extensionContext)) {
      attachScreenshot()
    }
    close()
  }

  private fun isTestResultNotSuccessful(extensionContext: ExtensionContext): Boolean {
    return extensionContext.executionException.isPresent
  }

  private fun getExecutionExceptionFromContext(extensionContext: ExtensionContext): String {
    return extensionContext.executionException.toString()
  }
}