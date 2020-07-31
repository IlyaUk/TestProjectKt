package mockcontrol.wiremock

import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import config.ApplicationConfig
import mockcontrol.MethodType
import mockcontrol.MockService
import mockcontrol.mockconfigs.MockConfigs
import mockcontrol.mockconfigs.WireMockConfig
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class WireMockService(private val config: ApplicationConfig) : MockService {
  private val log: Logger = LogManager.getLogger(WireMockService::class.simpleName)
  private val wireMockClient: WireMock = WireMock(config.wireMockHost, config.wireMockPort)

  override fun setMock(mockConfig: Any) {
    val selectedMockConfig: WireMockConfig = MockConfigs.valueOf(mockConfig.toString()).mockConfigType

    if (!verifyMock(mockConfig)) {
      val mapping: MappingBuilder = getMappingStub(selectedMockConfig)
      val stubMapping: StubMapping = wireMockClient.register(mapping)
      selectedMockConfig.id = stubMapping.id

      assert(verifyMock(mockConfig)) {
        "Could not add ${selectedMockConfig.mockName} mock"
      }
      log.info("Mock ${selectedMockConfig.mockName} is configured for the current environment")
    } else {
      log.error("Mock ${selectedMockConfig.mockName}/${selectedMockConfig.id} is already configured in WireMock")
    }
  }

  override fun removeMock(mockConfig: Any) {
    val selectedMockConfig: WireMockConfig = MockConfigs.valueOf(mockConfig.toString()).mockConfigType
    wireMockClient.removeStubMapping(wireMockClient.getStubMapping(selectedMockConfig.id).item)

    assert(!verifyMock(mockConfig)) {
      "Failed to remove ${selectedMockConfig.mockName}"
    }
    log.info("Mock ${selectedMockConfig.mockName} is removed")
  }

  override fun verifyMock(mockConfig: Any): Boolean {
    val selectedMockConfig: WireMockConfig = MockConfigs.valueOf(mockConfig.toString()).mockConfigType
    val actualWireMockStubs = wireMockClient.allStubMappings().mappings.map { it.id }

    return actualWireMockStubs.contains(selectedMockConfig.id)
  }

  private fun getMappingStub(selectedMockConfig: WireMockConfig): MappingBuilder {
    val mappingBuilder = WireMock.any(WireMock.urlMatching(selectedMockConfig.requestUrl))
        .atPriority(selectedMockConfig.priority)
        .withName(selectedMockConfig.mockName)
        .withBasicAuth(config.user, config.pass.toString())

    if (selectedMockConfig.methodType != MethodType.ANY_NO_RESPONSE) {
      val responseDefinitionBuilder = ResponseBuilder().buildResponseDefinition(selectedMockConfig)
      mappingBuilder.willReturn(responseDefinitionBuilder)
    }
    return mappingBuilder
  }
}