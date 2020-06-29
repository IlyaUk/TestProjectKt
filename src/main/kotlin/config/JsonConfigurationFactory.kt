package config

import utils.getClassObjectFromJson

class JsonConfigurationFactory : DefaultConfigurationFactory(), ConfigFactory {
  override val filePath: String = "config/json/base_configuration"
  override fun getConfig(): Configuration {
    return getClassObjectFromJson(filePath, Configuration::class.java)
  }
}