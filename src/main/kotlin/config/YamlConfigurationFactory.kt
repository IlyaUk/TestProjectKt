package config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue

class YamlConfigurationFactory : DefaultConfigurationFactory(), ConfigFactory {
  override fun getConfig(): Configuration {
    val objectMapperYAML = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
    return Thread.currentThread().contextClassLoader.getResourceAsStream(YAMLpath).use {
      objectMapperYAML.readValue(it)
    }
  }
}