package selenide

import utils.getClassObjectFromYaml

class DriverConfigProvider {
  private val defaultFilePath = "selenide_configs/selenide_driver_config.yaml"

  fun getDriverConfig(filePath: String = defaultFilePath): DriverConfiguration {
    val driverConfiguration = getClassObjectFromYaml(filePath, DriverConfiguration::class.java)
    return driverConfiguration
  }
}