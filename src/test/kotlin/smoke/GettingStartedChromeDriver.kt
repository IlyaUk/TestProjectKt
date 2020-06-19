package smoke

import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class GettingStartedChromeDriver {
    @Test
    fun testGoogleSearch() {
        //System.setProperty("webdriver.chrome.driver","C:\\SeleniumDrivers\\chromedriver.exe")
        val driver: WebDriver = ChromeDriver()
        driver.get("http://www.google.by/")
        Thread.sleep(5000)
        driver.quit()
    }
}