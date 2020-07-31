package mockcontrol

class MockServiceProvider(private val mockService: MockService) {
  fun verifyMock(mockConfig: Any): Any {
    return mockService.verifyMock(mockConfig)
  }

  fun setMock(mockConfig: Any) {
    mockService.setMock(mockConfig)
  }

  fun removeMock(mockConfig: Any) {
    mockService.removeMock(mockConfig)
  }
}