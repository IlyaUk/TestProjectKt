package config

open class ConfigurationProvider {
  enum class ConfigSource {
    YAML, JSON
  }

  companion object {
    fun setConfigType(type: ConfigSource): Configuration {
      return when (type) {
        ConfigSource.YAML -> YAMLConfiguration()
        ConfigSource.JSON -> JSONConfiguration()
      }
    }
  }
}