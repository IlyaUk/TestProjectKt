package elements

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.SelenideElement
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.By

object Slider {
  private val log: Logger = LogManager.getLogger(Slider::class.simpleName)

  fun setValueUsingSlider(element: By, xOffset: Int, yOffset: Int) {
    log.info("Set value using slider $element")
    val webElement: SelenideElement = `$`(element)
    actions().apply {
      dragAndDropBy(webElement, xOffset, yOffset).perform()
      release(webElement)
    }
  }

  fun setValueUsingSliderJS(sliderPoint: By, sliderLine: By, xOffsetMin: Double, xOffsetMax: Double) {
    log.info("Set value using slider with JS")
    Selenide().apply {
      executeJavaScript<Any>("arguments[0].setAttribute('style', 'left: $xOffsetMin%')", `$`(sliderPoint))
      executeJavaScript<Any>("arguments[0].setAttribute('style', 'width: $xOffsetMin%')", `$`(sliderLine))
      executeJavaScript<Any>("arguments[0].setAttribute('style', 'left: $xOffsetMax%')", `$`(sliderPoint))
      executeJavaScript<Any>("arguments[0].setAttribute('style', 'width: $xOffsetMax%')", `$`(sliderLine))
    }
  }
}