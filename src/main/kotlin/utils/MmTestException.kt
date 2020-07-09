package utils

class MmTestException(message: String) : Exception(message) {
  fun getExceptionMessage(): String? {
    return message
  }
}