package core

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule

class WebDriverConfigProvider {
  private val browserSystemProperty = "driver.browser"
  private val webDriverTypeProperty = "driver.type"
  fun getDriverConfig(): WebDriverConfiguration{
    val filePath = "wedbriver_configs/driver_config.yaml"
    fun getMapper(): ObjectMapper = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())
    val driverConfiguration: WebDriverConfiguration = Thread.currentThread().contextClassLoader.getResourceAsStream(filePath).use {
      getMapper().readValue(it, WebDriverConfiguration::class.java)}
    System.getProperty(browserSystemProperty)?.apply {
      driverConfiguration.browserType = BrowserType.valueOf(this.toUpperCase())
    }
    System.getProperty(webDriverTypeProperty)?.apply {
      driverConfiguration.webDriverType = WebDriverType.valueOf(this.toUpperCase())
    }
    return driverConfiguration
  }
}