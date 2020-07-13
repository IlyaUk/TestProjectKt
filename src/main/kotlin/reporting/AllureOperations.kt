package reporting

import com.codeborne.selenide.Selenide
import io.qameta.allure.Allure
import org.apache.commons.io.FileUtils.getFile
import org.apache.commons.io.FileUtils.readFileToByteArray
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

object AllureOperations {
  private val log: Logger = LogManager.getLogger(AllureOperations::class.simpleName)
  private const val screenshotName = "ScreenshotOnFail"
  private const val logPath = "src/main/resources/logfile.log"
  private const val screenshotPath = "build/reports/tests/$screenshotName.png"
  fun addStepToReport(stepName: String) {
    Allure.step(stepName)
  }

  private fun addScreenshotToReport(attachment: ByteArray) {
    Allure.getLifecycle().addAttachment("Screenshot", "image/png", "png", attachment)
  }

  private fun addLogToReport(logByteArray: ByteArray) {
    Allure.getLifecycle().addAttachment("LogsFiles", "log", "log", logByteArray)
  }

  fun attachLogsFile() {
    try {
      val pathToLogs: Path = Paths.get(logPath)
      addLogToReport(Files.readAllBytes(pathToLogs))
    } catch (e: IOException) {
      log.error(e.message)
    }
  }

  fun attachScreenshot() {
    Selenide.screenshot(screenshotName)
    try {
      val screenshotAsByteArray: ByteArray = readFileToByteArray(getFile(screenshotPath))
      addScreenshotToReport(screenshotAsByteArray)
    } catch (e: NullPointerException) {
      log.error(e.message)
    }
  }
}