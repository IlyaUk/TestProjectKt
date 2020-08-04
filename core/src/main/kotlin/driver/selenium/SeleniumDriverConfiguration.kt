package driver.selenium

import driver.config.WebDriverType
import driver.config.BrowserType
import driver.config.DriverConfig

data class SeleniumDriverConfiguration (
    val width: Int,
    val height: Int,
    val defaultTimeoutMilliseconds: Long,
    val webDriverHost: String,
    val webDriverPort: Int,
    var browserType: BrowserType,
    var webDriverType: WebDriverType
): DriverConfig