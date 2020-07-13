package reporting

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher
import java.util.*

class TestListener : TestWatcher {

  override fun testSuccessful(extensionContext: ExtensionContext) {
    AllureOperations().attachLogsFile()
  }

  override fun testDisabled(extensionContext: ExtensionContext, reason: Optional<String>) {
    AllureOperations().attachLogsFile()
  }

  override fun testFailed(extensionContext: ExtensionContext, errorCause: Throwable) {
    AllureOperations().attachLogsFile()
  }

  override fun testAborted(extensionContext: ExtensionContext, errorCause: Throwable) {
    AllureOperations().attachLogsFile()
  }
}