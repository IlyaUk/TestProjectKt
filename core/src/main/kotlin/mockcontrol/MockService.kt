package mockcontrol

import mockcontrol.mockconfigs.MockConfig

interface MockService {
  fun verifyMock(mockConfig: MockConfig): Any
  fun setMock(mockConfig: MockConfig): Any
  fun removeMock(mockConfig: MockConfig): Any
}