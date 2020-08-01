package mockcontrol

import mockcontrol.mockconfigs.MockConfig

class MockServiceProvider(private val mockService: MockService) {
  fun verifyMock(mockConfig: MockConfig): Any {
    return mockService.verifyMock(mockConfig)
  }

  fun setMock(mockConfig: MockConfig) {
    mockService.setMock(mockConfig)
  }

  fun removeMock(mockConfig: MockConfig) {
    mockService.removeMock(mockConfig)
  }
}