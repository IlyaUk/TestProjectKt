package elements

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions

object Slider {
  fun setValueUsingSlider(driver: WebDriver, xOffset: Int, yOffset: Int, element: By) {
    val actions = Actions(driver)
    actions.dragAndDropBy(driver.findElement(element), xOffset, yOffset).perform()
    actions.release(driver.findElement(element))
  }

  fun setValueUsingSliderJS(driver: WebDriver, xOffsetMin: Double, xOffsetMax: Double, element: By, element2: By) {
    val js = driver as JavascriptExecutor
    js.executeScript("arguments[0].setAttribute('style', 'left: $xOffsetMin%')", driver.findElement(element))
    js.executeScript("arguments[0].setAttribute('style', 'width: $xOffsetMin%')", driver.findElement(element2))

    js.executeScript("arguments[0].setAttribute('style', 'left: $xOffsetMax%')", driver.findElement(element))
    js.executeScript("arguments[0].setAttribute('style', 'width: $xOffsetMax%')", driver.findElement(element2))
  }
}