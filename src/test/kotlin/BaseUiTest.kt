import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import config.ConfigSource
import config.ApplicationConfig
import config.ConfigurationProvider
import elements.Navigation.close
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import driver.selenide.SelenideDriverManager
import org.junit.jupiter.api.BeforeEach

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseUiTest {
  protected lateinit var config: ApplicationConfig

  @BeforeAll
  fun setConfig() {
    config = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig()
    SelenideDriverManager().setSelenideWebDriverConfiguration()
    Configuration.baseUrl = config.getHostURL()
  }


  @BeforeEach
  fun domainAuth() {
    Selenide.open(config.getURLWithAuthorization())
  }

  @AfterEach
  fun quitDriver() {
    close()
  }
}