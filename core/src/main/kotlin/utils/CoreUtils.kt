package utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import httpclient.utils.SerializableRequest

fun <T> getClassObjectFromYaml(filePath: String, objectClass: Class<T>): T {
  val objectMapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
  return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath).use {
    objectMapper.readValue(it, objectClass)
  } ?: throw IllegalArgumentException("Unknown filepath")
}

fun <T> getClassObjectFromJson(filePath: String, objectClass: Class<T>): T {
  val objectMapper = ObjectMapper().registerModule(KotlinModule())
  return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath).use {
    objectMapper.readValue(it, objectClass)
  } ?: throw IllegalArgumentException("Unknown filepath")
}

fun <T> getClassObjectFromString(content: String, objectClass: Class<T>): T {
  val objectMapper = ObjectMapper().registerModule(KotlinModule())
  return objectMapper.readValue(content, objectClass)
}

fun convertObjectToJsonString(content: SerializableRequest): String {
  val objectMapper = ObjectMapper().registerModule(KotlinModule())
  return objectMapper.writeValueAsString(content)
}