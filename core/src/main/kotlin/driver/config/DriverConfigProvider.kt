package driver.config

import driver.selenide.SelenideDriverConfiguration
import driver.selenium.SeleniumDriverConfiguration
import utils.getClassObjectFromYaml

class DriverConfigProvider {

  companion object {
    const val DRIVER_TYPE_SYSTEM_PROPERTY: String = "webdriver.type"
    const val REMOTE_HOST_SYSTEM_PROPERTY: String = "webdriver.host"
    const val REMOTE_PORT_SYSTEM_PROPERTY: String = "webdriver.port"
    const val BROWSER_NAME_SYSTEM_PROPERTY: String = "browser.name"
    const val BROWSER_HEADLESS_SYSTEM_PROPERTY: String = "browser.headless"
  }

  private val seleniumBrowserSystemProperty = "selenium.browser"
  private val seleniumWebDriverTypeProperty = "selenium.type"
  private val defaultSeleniumFilePath = "wedbriver_configs/driver_config.yaml"
  private val defaultSelenideConfigFilePath = "selenide_configs/selenide_driver_config.yaml"
  private lateinit var driverConfiguration: SelenideDriverConfiguration

  fun getDriverConfig(type: DriverFrameworkType): DriverConfig {
    return when (type) {
      DriverFrameworkType.SELENIDE -> DriverConfigProvider().getSelenideDriverConfig()
      DriverFrameworkType.SELENIUM -> DriverConfigProvider().getSeleniumDriverConfig()
    }
  }

  fun getSelenideDriverConfig(filePath: String = defaultSelenideConfigFilePath): SelenideDriverConfiguration {
    driverConfiguration = getClassObjectFromYaml(filePath, SelenideDriverConfiguration::class.java)
    return driverConfiguration.apply {
      webDriverHost = System.getProperty(REMOTE_HOST_SYSTEM_PROPERTY) ?: driverConfiguration.webDriverHost
      webDriverPort = System.getProperty(REMOTE_PORT_SYSTEM_PROPERTY) ?: driverConfiguration.webDriverPort
      webDriverType = getDriverTypeConfig()
      browserType = getBrowserTypeConfig()
      headlessMode = getBrowserModeConfig()
    }
  }

  fun getSeleniumDriverConfig(filePath: String = defaultSeleniumFilePath): SeleniumDriverConfiguration {
    val driverConfiguration = getClassObjectFromYaml(filePath, SeleniumDriverConfiguration::class.java)

    System.getProperty(seleniumBrowserSystemProperty)?.apply {
      driverConfiguration.browserType = BrowserType.valueOf(this.toUpperCase())
    }
    System.getProperty(seleniumWebDriverTypeProperty)?.apply {
      driverConfiguration.webDriverType = WebDriverType.valueOf(this.toUpperCase())
    }
    return driverConfiguration
  }

  private fun getDriverTypeConfig(): WebDriverType {
    val driverTypeProperty: String? = System.getProperty(DRIVER_TYPE_SYSTEM_PROPERTY)
    return when {
      driverTypeProperty.isNullOrEmpty() -> driverConfiguration.webDriverType
      else -> WebDriverType.valueOf(driverTypeProperty.toUpperCase())
    }
  }

  private fun getBrowserTypeConfig(): BrowserType {
    val browserTypeProperty: String? = System.getProperty(BROWSER_NAME_SYSTEM_PROPERTY)
    return when {
      browserTypeProperty.isNullOrEmpty() -> driverConfiguration.browserType
      else -> BrowserType.valueOf(browserTypeProperty.toUpperCase())
    }
  }

  private fun getBrowserModeConfig(): Boolean {
    val browserModeArg: String? = System.getProperty(BROWSER_HEADLESS_SYSTEM_PROPERTY)
    return browserModeArg?.toBoolean() ?: driverConfiguration.headlessMode
  }
}