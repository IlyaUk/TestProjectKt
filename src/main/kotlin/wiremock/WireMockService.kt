package wiremock

import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import config.ApplicationConfig
import httpclient.utils.HeaderType
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class WireMockService(private val config: ApplicationConfig) {
  private val log: Logger = LogManager.getLogger(WireMockService::class.simpleName)
  private val wireMockClient: WireMock = WireMock(config.wireMockHost, config.wireMockPort)
  private lateinit var mappingBuilder: MappingBuilder

  fun setMock(mockConfig: MockConfig) {
    if (!isStubMappingPresentsInWireMock(mockConfig)) {
      val mapping: MappingBuilder = getMappingStub(mockConfig)
      val stubMapping: StubMapping = wireMockClient.register(mapping)
      mockConfig.id = stubMapping.id

      assert(isStubMappingPresentsInWireMock(mockConfig)) {
        "Could not add ${mockConfig.mockName} mock"
      }
      log.info("Mock ${mockConfig.mockName} is configured for the current environment")
    } else {
      log.error("Mock ${mockConfig.mockName}/${mockConfig.id} is already configured in WireMock")
    }
  }

  fun removeMock(mockConfig: MockConfig) {
    wireMockClient.removeStubMapping(wireMockClient.getStubMapping(mockConfig.id).item)

    assert(!isStubMappingPresentsInWireMock(mockConfig)) {
      "Failed to remove ${mockConfig.mockName}"
    }
    log.info("Mock ${mockConfig.mockName} is removed")
  }

  private fun getMappingStub(mockConfig: MockConfig): MappingBuilder {
    mappingBuilder = WireMock.any(WireMock.urlMatching(mockConfig.url))
        .atPriority(mockConfig.priority)
        .withName(mockConfig.mockName)
        .withBasicAuth(config.user, config.pass.toString())

    val responseDefinitionBuilder: ResponseDefinitionBuilder = WireMock.aResponse()
        .withStatus(mockConfig.responseStatusCode)
        .withHeader(HeaderType.CONTENT_TYPE.headerName, mockConfig.responseContentType)
        .withBodyFile(mockConfig.filePath)
    return mappingBuilder.willReturn(responseDefinitionBuilder)
  }

  private fun isStubMappingPresentsInWireMock(mockConfig: MockConfig): Boolean {
    val actualWireMockStubs = wireMockClient.allStubMappings().mappings.map { it.id }
    return actualWireMockStubs.contains(mockConfig.id)
  }
}