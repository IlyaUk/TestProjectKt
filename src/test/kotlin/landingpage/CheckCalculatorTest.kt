package landingpage

import config.ConfigSource
import config.ConfigurationProvider
import driver.WebDriverManager
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
  }

  @Test
  fun `check Calculator Block`() {
    val expectedDefaultCreditAmountValue = "2,000"
    val expectedDefaultCreditPeriodValue = "30"
    val expectedMinCreditAmountValue = "1,500"
    val expectedMinCreditPeriodValue = "7"
    val expectedMaxCreditAmountValue = "5,000"
    val expectedMaxCreditPeriodValue = "30"
    landingPage = LandingPage(driver)

    landingPage.calculator.open(username, password, host)

    Waiter().waitExplicitlyForElement(driver, landingPage.calculator.creditAmountSliderPoint)

    Assertions.assertEquals(expectedDefaultCreditAmountValue, landingPage.calculator.getActualCreditAmountValue())
    Assertions.assertEquals(expectedDefaultCreditPeriodValue, landingPage.calculator.getActualCreditPeriodValue())

    landingPage.calculator.setCreditAmountSlider(-100, 0)
    Assertions.assertEquals(expectedMinCreditAmountValue, landingPage.calculator.getActualCreditAmountValue())
    landingPage.calculator.setCreditAmountSlider(400, 0)
    Assertions.assertEquals(expectedMaxCreditAmountValue, landingPage.calculator.getActualCreditAmountValue())

    landingPage.calculator.setCreditPeriodSlider(-400, 0)
    Assertions.assertEquals(expectedMinCreditPeriodValue, landingPage.calculator.getActualCreditPeriodValue())
    landingPage.calculator.setCreditPeriodSlider(400, 0)
    Assertions.assertEquals(expectedMaxCreditPeriodValue, landingPage.calculator.getActualCreditPeriodValue())

    landingPage.calculator.clickTakeLoanButton()
    landingPage.calculator.close()
  }

  @Test
  fun `check Calculator Block With JS`() {
    val creditAmount = "4,000"
    val creditPeriod = "25"
    landingPage = LandingPage(driver)

    landingPage.calculator.open(username, password, host)

    Waiter().waitFluentlyForElement(driver, landingPage.calculator.creditAmountSliderPoint)

    landingPage.calculator.setCreditAmountSliderJS(0.0, 100.0)
    landingPage.calculator.setCreditPeriodSliderJS(0.0, 100.0)
    landingPage.calculator.setCreditValuesJS(creditAmount, creditPeriod)
    Assertions.assertEquals(creditAmount, landingPage.calculator.getActualCreditAmountValue())
    Assertions.assertEquals(creditPeriod, landingPage.calculator.getActualCreditPeriodValue())

    landingPage.calculator.clickTakeLoanButtonJS()
    landingPage.calculator.close()
  }
}