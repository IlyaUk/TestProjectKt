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
  fun createConfigObject(source: ConfigSource) {
    val configuration = ConfigurationProvider.setConfigType(source).getConfig()
    assertAll("configurationJSON",
        { assertEquals("moneyman", configuration.user) },
        { assertEquals(1005, configuration.pass) },
        { assertEquals("qa-delivery-mx-master.moneyman.ru", configuration.host) },
        { assertEquals("client-area/registration", configuration.registrationServiceEndpoint) },
        { assertEquals("private-area/static/#/login", configuration.privateAreaServiceEndpoint) },
        { assertEquals("/", configuration.landingPageServiceEndpoint) }
    )
  }
}
