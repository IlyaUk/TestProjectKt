package mockcontrol

interface MockService {
  fun verifyMock(mockConfig: Any): Any
  fun setMock(mockConfig: Any): Any
  fun removeMock(mockConfig: Any): Any
}