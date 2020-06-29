package config

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CreateConfigDynamicTest {
  @ParameterizedTest
  @EnumSource(names = ["YAML", "JSON"])
  @DisplayName("Create_Config_Object_From_Enum_Source")
  fun CreateConfigObject(source: ConfigSource) {
    val configurationObject = ConfigurationProvider.setConfigType(source).getConfig()
    assertAll("configurationObjectJSON",
        { assertEquals("moneyman", configurationObject.user) },
        { assertEquals(1005, configurationObject.pass) },
        { assertEquals("qa-delivery-mx-master.moneyman.ru", configurationObject.host) },
        { assertEquals("client-area/registration", configurationObject.registrationServiceEndpoint) },
        { assertEquals("private-area/static/#/login", configurationObject.privateAreaServiceEndpoint) }
    )
  }
}
