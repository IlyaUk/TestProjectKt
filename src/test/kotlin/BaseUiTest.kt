import config.ConfigSource
import config.Configuration
import config.ConfigurationProvider
import elements.Navigation.close
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.WebDriver

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseUiTest {
  protected lateinit var config: Configuration

  @BeforeAll
  fun setConfig() {
    config = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig()
  }

  @AfterEach
  fun quitWebDriver() {
    close()
  }
}