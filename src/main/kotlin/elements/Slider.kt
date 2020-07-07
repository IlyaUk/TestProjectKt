package elements

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.SelenideElement
import org.openqa.selenium.By

object Slider {
  fun setValueUsingSlider(element: By, xOffset: Int, yOffset: Int) {
    val webElement: SelenideElement = `$`(element)
    actions().apply {
      dragAndDropBy(webElement, xOffset, yOffset).perform()
      release(webElement)
    }
  }

  fun setValueUsingSliderJS(sliderPoint: By, sliderLine: By, xOffsetMin: Double, xOffsetMax: Double) {
    Selenide().apply {
      executeJavaScript<Any>("arguments[0].setAttribute('style', 'left: $xOffsetMin%')", `$`(sliderPoint))
      executeJavaScript<Any>("arguments[0].setAttribute('style', 'width: $xOffsetMin%')", `$`(sliderLine))
      executeJavaScript<Any>("arguments[0].setAttribute('style', 'left: $xOffsetMax%')", `$`(sliderPoint))
      executeJavaScript<Any>("arguments[0].setAttribute('style', 'width: $xOffsetMax%')", `$`(sliderLine))
    }
  }
}