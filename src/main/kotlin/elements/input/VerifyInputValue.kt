package elements.input

object VerifyInputValue {
  fun isTextInputMatchExpected(actualValue: String?, expectedValue: String): Boolean {
    return actualValue.toString() == expectedValue
  }
}