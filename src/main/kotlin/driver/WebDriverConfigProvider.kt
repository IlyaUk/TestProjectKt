package driver

import utils.getClassObjectFromYaml

class WebDriverConfigProvider {
  private val browserSystemProperty = "driver.browser"
  private val webDriverTypeProperty = "driver.type"
  private val defaultFilePath = "wedbriver_configs/driver_config.yaml"

  fun getDriverConfig(filePath: String = defaultFilePath): WebDriverConfiguration {
    val driverConfiguration = getClassObjectFromYaml(filePath, WebDriverConfiguration::class.java)

    System.getProperty(browserSystemProperty)?.apply {
      driverConfiguration.browserType = BrowserType.valueOf(this.toUpperCase())
    }
    System.getProperty(webDriverTypeProperty)?.apply {
      driverConfiguration.webDriverType = WebDriverType.valueOf(this.toUpperCase())
    }
    return driverConfiguration
  }
}