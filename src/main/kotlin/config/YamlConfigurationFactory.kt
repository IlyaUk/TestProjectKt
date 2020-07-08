package config

import utils.getClassObjectFromYaml

class YamlConfigurationFactory : DefaultConfigurationFactory(), ConfigFactory {
  override val filePath: String = "config/yaml/base_configuration"
  override fun getConfig(): ApplicationConfig {
    return getClassObjectFromYaml(filePath, ApplicationConfig::class.java)
  }
}