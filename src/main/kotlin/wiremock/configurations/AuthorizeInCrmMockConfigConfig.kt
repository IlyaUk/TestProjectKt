package wiremock.configurations

import config.ApplicationConfig
import wiremock.MockConfig
import java.util.*

class AuthorizeInCrmMockConfigConfig(private val config: ApplicationConfig) : MockConfig {
  override var id: UUID? = null
  override val priority: Int = 1
  override val mockName = "Authorize as admin in CRM"
  override val url = "/secure/rest/sign/in"
  override val responseStatusCode = 200
  override val responseContentType = "application/json"
  override val filePath = "successfulCrmLoginJson.json"
  override val basicAuthUser = config.user
  override val basicAuthPass = config.pass.toString()
}