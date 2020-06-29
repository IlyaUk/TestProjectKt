package config

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PrintConfigTest {
  @DisplayName("Print_Config_Object_From_Json")
  @Test
  fun PrintConfigObjectFromJson() {
    val config = ConfigurationProvider.setConfigType(ConfigSource.JSON).getConfig()
    println(config)
  }

  @DisplayName("Print_Config_Object_From_Yaml")
  @Test
  fun PrintConfigObjectFromYaml() {
    val config = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig()
    println(config)
  }
}