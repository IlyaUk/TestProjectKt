package landingPage

import config.ConfigSource
import config.ConfigurationProvider
import core.WebDriverContainer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import pages.LandingPage

class CheckCalculatorTest : LandingPage.CalculatorBlock() {
    val username = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig().user
    val password = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig().pass
    val host = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig().host

    @Test
    fun checkCalculatorDefaultValues() {
        //System.setProperty("webdriver.chrome.driver","C:\\SeleniumDrivers\\chromedriver.exe")
        val expectedDefaultCreditAmountValue = "2,000"
        val expectedDefaultCreditPeriodValue = "30"
        val expectedMinCreditAmountValue = "1,500"
        val expectedMinCreditPeriodValue = "7"
        val expectedMaxCreditAmountValue = "5,000"
        val expectedMaxCreditPeriodValue = "30"

        val driver = WebDriverContainer().getDriver()
        driver.get("https://$username:$password@$host")

        Thread.sleep(10000)
        Assertions.assertEquals(expectedDefaultCreditAmountValue, checkCalculatorAmountValue(driver))
        Assertions.assertEquals(expectedDefaultCreditPeriodValue, checkCalculatorPeriodValue(driver))

        moveCreditAmountSlider(driver, -100, 0)
        Assertions.assertEquals(expectedMinCreditAmountValue, checkCalculatorAmountValue(driver))

        moveCreditPeriodSlider(driver, -400, 0)
        Assertions.assertEquals(expectedMinCreditPeriodValue, checkCalculatorPeriodValue(driver))

        moveCreditAmountSlider(driver, 400, 0)
        Assertions.assertEquals(expectedMaxCreditAmountValue, checkCalculatorAmountValue(driver))

        moveCreditPeriodSlider(driver, 400, 0)
        Assertions.assertEquals(expectedMaxCreditPeriodValue, checkCalculatorPeriodValue(driver))

        Assertions.assertTrue(checkTakeLoanButtonAvailable(driver))

        driver.quit()
    }
}