package httpclient.utils

enum class HeaderType(val headerName: String) {
  AUTHORIZATION("Authorization"),
  CONTENT_TYPE("Content-Type"),
  COOKIE("Cookie")
}