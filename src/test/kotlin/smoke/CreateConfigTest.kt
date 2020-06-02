package smoke

import config.ConfigurationProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import config.ConfigSource

class CreateConfigTest {
  @DisplayName("Create_Config_Object_From_Json")
  @Test
  fun CreateConfigObjectFromJson() {
    val configurationObjectJSON = ConfigurationProvider.setConfigType(ConfigSource.JSON)
        .getConfig()
    assertAll("configurationObjectJSON",
        { assertEquals("moneyman", configurationObjectJSON.user) },
        { assertEquals(100500, configurationObjectJSON.pass) },
        { assertEquals("qa-delivery-master.mm.ru", configurationObjectJSON.host) },
        { assertEquals("/client-area/registration", configurationObjectJSON.registrationServiceEndpoint) },
        { assertEquals(0, configurationObjectJSON.phoneNumber) }
    )
  }

  @DisplayName("Create_Config_Object_From_Yaml")
  @Test
  fun CreateConfigObjectFromYaml() {
    val configurationObjectYAML = ConfigurationProvider.setConfigType(ConfigSource.JSON)
        .getConfig()
    assertAll("configurationObjectYAML",
        { assertEquals("moneyman", configurationObjectYAML.user) },
        { assertEquals(100500, configurationObjectYAML.pass) },
        { assertEquals("qa-delivery-master.mm.ru", configurationObjectYAML.host) },
        { assertEquals("/client-area/registration", configurationObjectYAML.registrationServiceEndpoint) }
    )
  }
}