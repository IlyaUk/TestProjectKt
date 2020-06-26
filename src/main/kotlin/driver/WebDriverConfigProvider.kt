package driver

import utils.getClassObjectFromYaml

class WebDriverConfigProvider {
  private val browserSystemProperty = "driver.browser"
  private val webDriverTypeProperty = "driver.type"

  fun getDriverConfig(filePath: String = "wedbriver_configs/driver_config.yaml"): WebDriverConfiguration {
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