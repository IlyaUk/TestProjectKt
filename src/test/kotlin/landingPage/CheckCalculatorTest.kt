package landingPage

import config.ConfigSource
import config.ConfigurationProvider
import core.Waiter
import core.WebDriverContainer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import pages.LandingPage

class CheckCalculatorTest {
  val username = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig().user
  val password = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig().pass
  val host = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig().host
  lateinit var landingPage: LandingPage

  @Test
  fun checkCalculatorBlock() {
    System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe")
    System.setProperty("webdriver.gecko.driver", "C:\\SeleniumDrivers\\geckodriver.exe")
    val expectedDefaultCreditAmountValue = "2,000"
    val expectedDefaultCreditPeriodValue = "30"
    val expectedMinCreditAmountValue = "1,500"
    val expectedMinCreditPeriodValue = "7"
    val expectedMaxCreditAmountValue = "5,000"
    val expectedMaxCreditPeriodValue = "30"
    val driverType = WebDriverContainer.WebDrivers.Chrome
    landingPage = LandingPage()

    val driver = WebDriverContainer().getDriver(driverType)
    driver.get("https://$username:$password@$host")
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

    driver.quit()
  }

  @Test
  fun checkCalculatorBlockWithJS() {
    System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe")
    System.setProperty("webdriver.gecko.driver", "C:\\SeleniumDrivers\\geckodriver.exe")
    val creditAmount = "4,000"
    val creditPeriod = "25"
    val driverType = WebDriverContainer.WebDrivers.Firefox
    landingPage = LandingPage()

    val driver = WebDriverContainer().getDriver(driverType)
    driver.get("https://$username:$password@$host")
    Waiter().waitFluentlyForElement(driver, landingPage.calculator.creditAmountSlider)

    landingPage.calculator.setCreditAmountSliderJS(driver, 0.0, 100.0)
    landingPage.calculator.setCreditPeriodSliderJS(driver, 0.0, 100.0)

    landingPage.calculator.setCreditAmountJS(driver, creditAmount)
    Assertions.assertEquals(creditAmount, landingPage.calculator.getCalculatorAmountValue(driver))
    landingPage.calculator.setCreditPeriodJS(driver, creditPeriod)
    Assertions.assertEquals(creditPeriod, landingPage.calculator.getCalculatorPeriodValue(driver))

    Assertions.assertTrue(landingPage.calculator.isTakeLoanButtonAvailable(driver))
    landingPage.calculator.clickTakeLoanButtonJS(driver)

    driver.quit()
  }
}