package config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue

class JsonConfigurationFactory : DefaultConfigurationFactory(), ConfigFactory {
  override fun getConfig(): Configuration {
    val objectMapperJSON = ObjectMapper().registerModule(KotlinModule())
    return Thread.currentThread().contextClassLoader.getResourceAsStream(JSONpath).use {
      objectMapperJSON.readValue(it)
    }
  }
}