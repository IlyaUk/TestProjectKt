import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.logevents.LogEventListener
import com.codeborne.selenide.logevents.SelenideLogger
import config.ApplicationConfig
import config.ConfigSource
import config.ConfigurationProvider
import driver.selenide.SelenideDriverManager
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import reporting.TestListener

@ExtendWith(TestListener::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseUiTest {
  protected lateinit var config: ApplicationConfig

  @BeforeAll
  fun setConfig() {
    config = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig()
    SelenideDriverManager().setSelenideWebDriverConfiguration()
    Configuration.baseUrl = config.getHostURL()
    SelenideLogger.addListener(
        "AllureSelenide", AllureSelenide()
        .screenshots(true)
        .savePageSource(false)
    )
  }

  @AfterAll()
  fun cleanConfig() {
    SelenideLogger.removeListener<LogEventListener>("AllureSelenide")
  }

  @BeforeEach
  fun domainAuth() {
    Selenide.open(config.getURLWithAuthorization())
  }
}