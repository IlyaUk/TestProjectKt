package wiremock

import java.util.*

interface MockConfig {
  var id: UUID?
  val priority: Int
  val mockName: String
  val url: String
  val responseStatusCode: Int
  val responseContentType: String
  val filePath: String
  val basicAuthUser: String
  val basicAuthPass: String
}