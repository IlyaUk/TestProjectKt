package mockcontrol.mockconfigs

import java.util.*

interface MockConfig {
  var id: UUID?
  val priority: Int?
  val mockName: String?
  val requestUrl: String?
  val responseStatusCode: Int?
  val responseContentType: String?
  val responseFilePath: String?
}