package config

import utils.getClassObjectFromYaml

class YamlConfigurationFactory : DefaultConfigurationFactory(), ConfigFactory {
  override val filePath: String = "config/yaml/base_configuration"
  override fun getConfig(): Configuration {
    return getClassObjectFromYaml(filePath, Configuration::class.java)
  }
}