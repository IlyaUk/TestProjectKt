package driver

import utils.getClassObjectFromYaml

class WebDriverConfigProvider {
  private val browserSystemProperty = "driver.browser"
  private val webDriverTypeProperty = "driver.type"
  private val filePath = "wedbriver_configs/driver_config.yaml"
  private val driverConfiguration: WebDriverConfiguration = getClassObjectFromYaml(filePath, WebDriverConfiguration::class.java)

  fun getDriverConfig(): WebDriverConfiguration {
    System.getProperty(browserSystemProperty)?.apply {
      driverConfiguration.browserType = BrowserType.valueOf(this.toUpperCase())
    }
    System.getProperty(webDriverTypeProperty)?.apply {
      driverConfiguration.webDriverType = WebDriverType.valueOf(this.toUpperCase())
    }
    return driverConfiguration
  }
}