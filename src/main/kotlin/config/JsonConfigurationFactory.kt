package config

import utils.getClassObjectFromJson

class JsonConfigurationFactory : DefaultConfigurationFactory(), ConfigFactory {
  override val filePath: String = "config/json/base_configuration"
  override fun getConfig(): ApplicationConfig {
    return getClassObjectFromJson(filePath, ApplicationConfig::class.java)
  }
}