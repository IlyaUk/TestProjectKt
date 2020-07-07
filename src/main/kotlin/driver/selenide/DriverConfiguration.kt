package driver.selenide

import driver.config.BrowserType

data class DriverConfiguration(
    val browserScreenSize: String,
    val defaultTimeoutMilliseconds: Long,
    val webDriverHost: String,
    val webDriverPort: Int,
    var browserType: BrowserType,
    var webDriverType: DriverType,
    var headlessMode: Boolean,
    var browserStartMaximize: Boolean,
    var browserPageLoadStrategy: String
)