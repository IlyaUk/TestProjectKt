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
import elements.Button
import elements.Input
import elements.Slider
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

    Waiter().waitExplicitlyForElement(driver, landingPage.calculator.creditAmountSliderPoint)

    Assertions.assertEquals(expectedDefaultCreditAmountValue, Input.getCalculatorAmountValue(driver, landingPage.calculator.calculatorAmount))
    Assertions.assertEquals(expectedDefaultCreditPeriodValue, Input.getCalculatorPeriodValue(driver, landingPage.calculator.calculatorPeriod))

    Slider.setCreditAmountSlider(driver, -100, 0, landingPage.calculator.creditAmountSliderPoint)
    Assertions.assertEquals(expectedMinCreditAmountValue, Input.getCalculatorAmountValue(driver, landingPage.calculator.calculatorAmount))
    Slider.setCreditAmountSlider(driver, 400, 0, landingPage.calculator.creditAmountSliderPoint)
    Assertions.assertEquals(expectedMaxCreditAmountValue, Input.getCalculatorAmountValue(driver, landingPage.calculator.calculatorAmount))

    Slider.setCreditPeriodSlider(driver, -400, 0, landingPage.calculator.creditPeriodSliderPoint)
    Assertions.assertEquals(expectedMinCreditPeriodValue, Input.getCalculatorPeriodValue(driver, landingPage.calculator.calculatorPeriod))
    Slider.setCreditPeriodSlider(driver, 400, 0, landingPage.calculator.creditPeriodSliderPoint)
    Assertions.assertEquals(expectedMaxCreditPeriodValue, Input.getCalculatorPeriodValue(driver, landingPage.calculator.calculatorPeriod))

    Assertions.assertTrue(Button.isTakeLoanButtonAvailable(driver, landingPage.calculator.takeLoanButton))
    Button.clickTakeLoanButton(driver, landingPage.calculator.takeLoanButton)
  }

  @Test
  fun checkCalculatorBlockWithJS() {
    val creditAmount = "4,000"
    val creditPeriod = "25"
    landingPage = LandingPage()

    Waiter().waitFluentlyForElement(driver, landingPage.calculator.creditAmountSliderPoint)

    Slider.setCreditAmountSliderJS(driver, 0.0, 100.0, landingPage.calculator.creditAmountSliderPoint, landingPage.calculator.creditAmountSliderLine)
    Slider.setCreditPeriodSliderJS(driver, 0.0, 100.0, landingPage.calculator.creditPeriodSliderPoint, landingPage.calculator.creditPeriodSliderLine)

    Input.setCreditAmountJS(driver, creditAmount)
    Assertions.assertEquals(creditAmount, Input.getCalculatorAmountValue(driver, landingPage.calculator.calculatorAmount))
    Input.setCreditPeriodJS(driver, creditPeriod)
    Assertions.assertEquals(creditPeriod, Input.getCalculatorPeriodValue(driver, landingPage.calculator.calculatorPeriod))

    Assertions.assertTrue(Button.isTakeLoanButtonAvailable(driver, landingPage.calculator.takeLoanButton))
    Button.clickTakeLoanButtonJS(driver, landingPage.calculator.takeLoanButton)
  }
}