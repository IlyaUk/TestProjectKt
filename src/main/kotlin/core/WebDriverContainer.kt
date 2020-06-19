package core

import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

open class WebDriverContainer {
    fun getDriver(): WebDriver {
        val driver = ChromeDriver()
        driver.manage().window().size = Dimension(1600, 900)
        return driver
    }
}