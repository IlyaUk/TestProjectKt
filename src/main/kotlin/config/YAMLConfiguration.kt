package config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue

class YAMLConfiguration : Configuration {
  private val YAMLpath = "config/yaml/base_configuration"
  private val objectMapperYAML = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())

  override fun getConfig(): ConfigurationObject {
    return Thread.currentThread().contextClassLoader.getResourceAsStream(YAMLpath).use {
      objectMapperYAML.readValue(it)
    }
  }
}