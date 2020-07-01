import config.ConfigSource
import config.Configuration
import config.ConfigurationProvider
import driver.WebDriverManager
import elements.Navigation.close
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.WebDriver

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseUiTest {
  protected lateinit var configObject: Configuration
  private lateinit var driver: WebDriver

  @BeforeAll
  fun setConfig() {
    configObject = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig()
  }

  @BeforeEach
  fun getWebDriver() {
    driver = WebDriverManager.getDriver()
  }

  @AfterEach
  fun quitWebDriver() {
    close()
  }
}