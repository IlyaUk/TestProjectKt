package driver.selenide

import driver.config.BrowserType
import driver.config.DriverConfig
import driver.config.WebDriverType

data class SelenideDriverConfiguration(
    val browserScreenSize: String,
    val defaultTimeoutMilliseconds: Long,
    val webDriverHost: String,
    val webDriverPort: Int,
    var browserType: BrowserType,
    var webDriverType: WebDriverType,
    var headlessMode: Boolean,
    var browserStartMaximize: Boolean,
    var browserPageLoadStrategy: String
): DriverConfig