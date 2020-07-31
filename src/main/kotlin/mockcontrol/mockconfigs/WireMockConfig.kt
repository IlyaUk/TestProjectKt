package mockcontrol.mockconfigs

import mockcontrol.MethodType
import java.util.*

interface WireMockConfig {
  var id: UUID?
  val priority: Int
  val mockName: String
  val requestUrl: String
  val responseStatusCode: Int
  val methodType: MethodType
  val responseContentType: String?
  val responseFilePath: String?
}

enum class MockConfigs(val mockConfigType: WireMockConfig) {
  POST_CRM_AUTHORIZE(AuthorizeInCrmWireMockConfig)
}