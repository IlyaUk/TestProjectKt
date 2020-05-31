package config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue

class JSONConfiguration : Configuration {
  private val JSONpath = "config/json/base_configuration"
  private val objectMapperJSON = ObjectMapper().registerModule(KotlinModule())

  override fun getConfig(): ConfigurationObject {
    return Thread.currentThread().contextClassLoader.getResourceAsStream(JSONpath).use {
      objectMapperJSON.readValue(it)
    }
  }
}