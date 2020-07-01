package elements

import driver.WebDriverManager
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.interactions.Actions

object Slider {
  fun setValueUsingSlider(element: By, xOffset: Int, yOffset: Int) {
    val driver = WebDriverManager.getDriver()
    val actions = Actions(driver)
    actions.dragAndDropBy(driver.findElement(element), xOffset, yOffset).perform()
    actions.release(driver.findElement(element))
  }

  fun setValueUsingSliderJS(sliderPoint: By, sliderLine: By, xOffsetMin: Double, xOffsetMax: Double) {
    val driver = WebDriverManager.getDriver()
    val js = driver as JavascriptExecutor
    js.executeScript("arguments[0].setAttribute('style', 'left: $xOffsetMin%')", driver.findElement(sliderPoint))
    js.executeScript("arguments[0].setAttribute('style', 'width: $xOffsetMin%')", driver.findElement(sliderLine))

    js.executeScript("arguments[0].setAttribute('style', 'left: $xOffsetMax%')", driver.findElement(sliderPoint))
    js.executeScript("arguments[0].setAttribute('style', 'width: $xOffsetMax%')", driver.findElement(sliderLine))
  }
}