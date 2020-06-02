package config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule

class YamlConfigurationFactory : DefaultConfigurationFactory(), ConfigFactory {
    override val filePath: String = "config/yaml/base_configuration"
    override fun getMapper(): ObjectMapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
}