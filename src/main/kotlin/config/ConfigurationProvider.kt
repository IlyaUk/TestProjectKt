package config

open class ConfigurationProvider {
  companion object {
    fun setConfigType(type: ConfigSource): ConfigFactory {
      return when (type) {
        ConfigSource.YAML -> YamlConfigurationFactory()
        ConfigSource.JSON -> JsonConfigurationFactory()
      }
    }
  }
}