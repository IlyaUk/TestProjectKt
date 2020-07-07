package driver

data class WebDriverConfiguration (
    val width: Int,
    val height: Int,
    val defaultTimeoutMilliseconds: Long,
    val webDriverHost: String,
    val webDriverPort: Int,
    var browserType: BrowserType,
    var webDriverType: WebDriverType
)