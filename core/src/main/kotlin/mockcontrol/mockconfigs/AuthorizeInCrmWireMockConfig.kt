package mockcontrol.mockconfigs

import java.util.*

object AuthorizeInCrmWireMockConfig : MockConfig {
  override var id: UUID? = null
  override val priority: Int = 1
  override val requestUrl = "/secure/rest/sign/in"
  override val responseStatusCode = 200
  override val mockName = "Authorize as admin in CRM"
  override val responseContentType = "application/json"
  override val responseFilePath = "successfulCrmLoginJson.json"
}