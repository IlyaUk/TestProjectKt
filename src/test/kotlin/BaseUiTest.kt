import config.ConfigSource
import config.Configuration
import config.ConfigurationProvider
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import selenide.DriverManager

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseUiTest {
  protected lateinit var config: Configuration

  @BeforeAll
  fun setConfig() {
    config = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig()
    DriverManager().setSelenideWebDriverConfiguration()
  }
}