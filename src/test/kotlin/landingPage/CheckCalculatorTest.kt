package landingPage

import config.ConfigSource
import config.ConfigurationProvider
import core.WebDriverContainer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import pages.LandingPage

class CheckCalculatorTest {
    val username = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig().user
    val password = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig().pass
    val host = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig().host
    lateinit var landingPage:LandingPage

    @Test
    fun checkCalculatorDefaultValues() {
        val expectedDefaultCreditAmountValue = "2,000"
        val expectedDefaultCreditPeriodValue = "30"
        val expectedMinCreditAmountValue = "1,500"
        val expectedMinCreditPeriodValue = "7"
        val expectedMaxCreditAmountValue = "5,000"
        val expectedMaxCreditPeriodValue = "30"
        landingPage = LandingPage()

        val driver = WebDriverContainer().getDriver()
        driver.get("https://$username:$password@$host")
        val wait = WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(landingPage.calculator.creditAmountSlider))

        Assertions.assertEquals(expectedDefaultCreditAmountValue, landingPage.calculator.checkCalculatorAmountValue(driver))
        Assertions.assertEquals(expectedDefaultCreditPeriodValue, landingPage.calculator.checkCalculatorPeriodValue(driver))

        landingPage.calculator.moveCreditAmountSlider(driver, -100, 0)
        Assertions.assertEquals(expectedMinCreditAmountValue, landingPage.calculator.checkCalculatorAmountValue(driver))
        landingPage.calculator.moveCreditAmountSlider(driver, 400, 0)
        Assertions.assertEquals(expectedMaxCreditAmountValue, landingPage.calculator.checkCalculatorAmountValue(driver))

        landingPage.calculator.moveCreditPeriodSlider(driver, -400, 0)
        Assertions.assertEquals(expectedMinCreditPeriodValue, landingPage.calculator.checkCalculatorPeriodValue(driver))
        landingPage.calculator.moveCreditPeriodSlider(driver, 400, 0)
        Assertions.assertEquals(expectedMaxCreditPeriodValue, landingPage.calculator.checkCalculatorPeriodValue(driver))

        Assertions.assertTrue(landingPage.calculator.checkTakeLoanButtonAvailable(driver))

        driver.quit()
    }
}