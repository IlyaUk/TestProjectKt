package driver

data class WebDriverConfiguration (
    val width: Int,
    val height: Int,
    val defaultTimeoutSec: Long,
    val webDriverHost: String,
    val webDriverPort: Int,
    var browserType: BrowserType,
    var webDriverType: WebDriverType
)