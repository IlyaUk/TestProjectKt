package driver.config

import driver.selenide.DriverConfiguration
import driver.selenium.WebDriverConfiguration
import utils.getClassObjectFromYaml

class DriverConfigProvider {
  private val seleniumBrowserSystemProperty = "selenium.browser"
  private val seleniumWebDriverTypeProperty = "selenium.type"
  private val defaultSeleniumFilePath = "wedbriver_configs/driver_config.yaml"
  private val defaultSelenideConfigFilePath = "selenide_configs/selenide_driver_config.yaml"

  fun getDriverConfig(type: DriverFrameworkType): Any {
    return when (type) {
      DriverFrameworkType.SELENIDE -> DriverConfigProvider().getSelenideDriverConfig()
      DriverFrameworkType.SELENIUM -> DriverConfigProvider().getSeleniumDriverConfig()
    }
  }

  fun getSelenideDriverConfig(filePath: String = defaultSelenideConfigFilePath): DriverConfiguration {
    val driverConfiguration = getClassObjectFromYaml(filePath, DriverConfiguration::class.java)
    return driverConfiguration
  }

  fun getSeleniumDriverConfig(filePath: String = defaultSeleniumFilePath): WebDriverConfiguration {
    val driverConfiguration = getClassObjectFromYaml(filePath, WebDriverConfiguration::class.java)

    System.getProperty(seleniumBrowserSystemProperty)?.apply {
      driverConfiguration.browserType = BrowserType.valueOf(this.toUpperCase())
    }
    System.getProperty(seleniumWebDriverTypeProperty)?.apply {
      driverConfiguration.webDriverType = WebDriverType.valueOf(this.toUpperCase())
    }
    return driverConfiguration
  }
}