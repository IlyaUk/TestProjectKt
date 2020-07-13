package reporting

import com.codeborne.selenide.Selenide
import io.qameta.allure.Allure
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class AllureOperations {
  fun addStepToReport(stepName: String) {
    Allure.step(stepName)
  }

  private fun addScreenshotToReportOnFail(attachment: ByteArray) {
    Allure.getLifecycle().addAttachment("Screenshot", "image/png", "png", attachment)
  }

  private fun addLogsToReport(logsByteArray: ByteArray) {
    Allure.getLifecycle().addAttachment("LogsFiles", "log", "log", logsByteArray)
  }

  fun attachLogsFile() {
    try {
      val pathToLogs: Path = Paths.get("src/main/resources/logfile.log")
      AllureOperations().addLogsToReport(Files.readAllBytes(pathToLogs))
    } catch (e: IOException) {
      println(e)
    }
  }

  fun attachScreenshot() {
    val screenshot: ByteArray = Selenide.screenshot("Screenshot").toByteArray()
    AllureOperations().addScreenshotToReportOnFail(screenshot)
  }
}