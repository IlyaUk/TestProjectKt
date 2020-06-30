package elements

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions

object Slider {
  fun setValueUsingSlider(driver: WebDriver, element: By, xOffset: Int, yOffset: Int) {
    val actions = Actions(driver)
    actions.dragAndDropBy(driver.findElement(element), xOffset, yOffset).perform()
    actions.release(driver.findElement(element))
  }

  fun setValueUsingSliderJS(driver: WebDriver, sliderPoint: By, sliderLine: By, xOffsetMin: Double, xOffsetMax: Double) {
    val js = driver as JavascriptExecutor
    js.executeScript("arguments[0].setAttribute('style', 'left: $xOffsetMin%')", driver.findElement(sliderPoint))
    js.executeScript("arguments[0].setAttribute('style', 'width: $xOffsetMin%')", driver.findElement(sliderLine))

    js.executeScript("arguments[0].setAttribute('style', 'left: $xOffsetMax%')", driver.findElement(sliderPoint))
    js.executeScript("arguments[0].setAttribute('style', 'width: $xOffsetMax%')", driver.findElement(sliderLine))
  }
}