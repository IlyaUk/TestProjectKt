package mockcontrol.wiremock

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import httpclient.utils.HeaderType
import mockcontrol.mockconfigs.WireMockConfig

class ResponseBuilder {

  fun buildResponseDefinition(selectedMockConfig: WireMockConfig): ResponseDefinitionBuilder {
    return WireMock.aResponse()
        .withStatus(selectedMockConfig.responseStatusCode)
        .withHeader(HeaderType.CONTENT_TYPE.headerName, selectedMockConfig.responseContentType)
        .withBodyFile(selectedMockConfig.responseFilePath)
  }
}