package mockcontrol.wiremock

import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import config.ApplicationConfig
import mockcontrol.MockService
import mockcontrol.mockconfigs.MockConfig
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class WireMockService(private val config: ApplicationConfig) : MockService {
  private val log: Logger = LogManager.getLogger(WireMockService::class.simpleName)
  private val wireMockClient: WireMock = WireMock(config.wireMockHost, config.wireMockPort)
  private val responseBuilder = ResponseBuilder()

  override fun setMock(mockConfig: MockConfig) {
    if (!verifyMock(mockConfig)) {
      val mapping: MappingBuilder = getMappingStub(mockConfig)
      val stubMapping: StubMapping = wireMockClient.register(mapping)
      mockConfig.id = stubMapping.id
    } else {
      log.error("Mock ${mockConfig.mockName}/${mockConfig.id} is already configured in WireMock")
    }
  }

  override fun removeMock(mockConfig: MockConfig) {
    wireMockClient.removeStubMapping(wireMockClient.getStubMapping(mockConfig.id).item)

    assert(!verifyMock(mockConfig)) {
      "Failed to remove ${mockConfig.mockName}"
    }
    log.info("Mock ${mockConfig.mockName} is removed")
  }

  override fun verifyMock(mockConfig: MockConfig): Boolean {
    val actualWireMockStubs = wireMockClient.allStubMappings().mappings.map { it.id }

    return actualWireMockStubs.contains(mockConfig.id)
  }

  private fun getMappingStub(mockConfig: MockConfig): MappingBuilder {
    val mappingBuilder = WireMock.any(WireMock.urlMatching(mockConfig.requestUrl))
        .atPriority(mockConfig.priority)
        .withName(mockConfig.mockName)
        .withBasicAuth(config.user, config.pass.toString())
    val responseDefinitionBuilder = responseBuilder.buildResponseDefinition(mockConfig)

    return mappingBuilder.willReturn(responseDefinitionBuilder)
  }
}