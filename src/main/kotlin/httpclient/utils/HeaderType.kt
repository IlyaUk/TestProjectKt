package httpclient.utils

enum class HeaderType(val headerName: String) {
  AUTHORIZATION("Authorization"),
  CONTENTTYPE("Content-Type"),
  COOKIE("Cookie")
}