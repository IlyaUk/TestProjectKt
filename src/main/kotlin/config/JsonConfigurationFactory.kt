package config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

class JsonConfigurationFactory : DefaultConfigurationFactory(), ConfigFactory {
    override val filePath: String = "config/json/base_configuration"
    override fun getMapper(): ObjectMapper = ObjectMapper().registerModule(KotlinModule())
}