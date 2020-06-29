package landingPage

import config.ConfigSource
import config.ConfigurationProvider
import driver.WebDriverManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebDriver
import pages.LandingPage
import utils.Waiter

class CheckCalculatorTest {
  private val configObject = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig()
  private val username = configObject.user
  private val password = configObject.pass
  private val host = configObject.host
  private lateinit var landingPage: LandingPage
  private lateinit var driver: WebDriver

  @BeforeEach
  fun getWebDriver() {
    driver = WebDriverManager().getDriver()
    driver.get("https://$username:$password@$host")
  }

  @AfterEach
  fun quitWebDriver() {
    driver.quit()
  }

  @Test
  fun checkCalculatorBlock() {
    val expectedDefaultCreditAmountValue = "2,000"
    val expectedDefaultCreditPeriodValue = "30"
    val expectedMinCreditAmountValue = "1,500"
    val expectedMinCreditPeriodValue = "7"
    val expectedMaxCreditAmountValue = "5,000"
    val expectedMaxCreditPeriodValue = "30"
    landingPage = LandingPage()

    Waiter().waitExplicitlyForElement(driver, landingPage.calculator.creditAmountSlider)

    Assertions.assertEquals(expectedDefaultCreditAmountValue, landingPage.calculator.getCalculatorAmountValue(driver))
    Assertions.assertEquals(expectedDefaultCreditPeriodValue, landingPage.calculator.getCalculatorPeriodValue(driver))

    landingPage.calculator.setCreditAmountSlider(driver, -100, 0)
    Assertions.assertEquals(expectedMinCreditAmountValue, landingPage.calculator.getCalculatorAmountValue(driver))
    landingPage.calculator.setCreditAmountSlider(driver, 400, 0)
    Assertions.assertEquals(expectedMaxCreditAmountValue, landingPage.calculator.getCalculatorAmountValue(driver))

    landingPage.calculator.setCreditPeriodSlider(driver, -400, 0)
    Assertions.assertEquals(expectedMinCreditPeriodValue, landingPage.calculator.getCalculatorPeriodValue(driver))
    landingPage.calculator.setCreditPeriodSlider(driver, 400, 0)
    Assertions.assertEquals(expectedMaxCreditPeriodValue, landingPage.calculator.getCalculatorPeriodValue(driver))

    Assertions.assertTrue(landingPage.calculator.isTakeLoanButtonAvailable(driver))
  }

  @Test
  fun checkCalculatorBlockWithJS() {
    val creditAmount = "4,000"
    val creditPeriod = "25"
    landingPage = LandingPage()

    Waiter().waitFluentlyForElement(driver, landingPage.calculator.creditAmountSlider)

    landingPage.calculator.setCreditAmountSliderJS(driver, 0.0, 100.0)
    landingPage.calculator.setCreditPeriodSliderJS(driver, 0.0, 100.0)

    landingPage.calculator.setCreditAmountJS(driver, creditAmount)
    Assertions.assertEquals(creditAmount, landingPage.calculator.getCalculatorAmountValue(driver))
    landingPage.calculator.setCreditPeriodJS(driver, creditPeriod)
    Assertions.assertEquals(creditPeriod, landingPage.calculator.getCalculatorPeriodValue(driver))

    Assertions.assertTrue(landingPage.calculator.isTakeLoanButtonAvailable(driver))
    landingPage.calculator.clickTakeLoanButtonJS(driver)
  }
}