package config

object ConfigurationProvider {
    fun setConfigType(type: ConfigSource): ConfigFactory {
        return when (type) {
            ConfigSource.YAML -> YamlConfigurationFactory()
            ConfigSource.JSON -> JsonConfigurationFactory()
        }
    }
}