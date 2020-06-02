package config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

abstract class DefaultConfigurationFactory {
    abstract val filePath: String
    abstract fun getMapper(): ObjectMapper
    fun getConfig(): Configuration {
        return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath).use {
            getMapper().readValue(it)
        }
    }
}