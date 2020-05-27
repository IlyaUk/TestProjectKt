package config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue

open class ConfigurationProvider {
    private val JSONpath = "config/json/base_configuration"
    private val YAMLpath = "config/yaml/base_configuration"
    private val objectMapperJSON = ObjectMapper().registerModule(KotlinModule())
    private val objectMapperYAML = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())

    fun getObjectFromJSONFile(): ConfigurationObject {
        return Thread.currentThread().contextClassLoader.getResourceAsStream(JSONpath).use {
            objectMapperJSON.readValue(it)
        }
    }

    fun getObjectFromYAMLFile(): ConfigurationObject {
        return Thread.currentThread().contextClassLoader.getResourceAsStream(YAMLpath).use {
            objectMapperYAML.readValue(it)
        }
    }
}