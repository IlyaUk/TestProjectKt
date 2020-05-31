package smoke

import config.ConfigurationProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CreateConfigTest {
  @DisplayName("Create_Config_Object_From_Json")
  @Test
  fun CreateConfigObjectFromJson() {
    val configurationObjectJSON = ConfigurationProvider.setConfigType(ConfigurationProvider.ConfigSource.JSON)
        .getConfig()
    assertEquals(configurationObjectJSON.user, "moneyman")
    assertEquals(configurationObjectJSON.pass, 100500)
    assertEquals(configurationObjectJSON.host, "qa-delivery-master.mm.ru")
    assertEquals(configurationObjectJSON.registrationServiceEndpoint, "/client-area/registration")
    assertEquals(configurationObjectJSON.phoneNumber, 0)
  }

  @DisplayName("Create_Config_Object_From_Yaml")
  @Test
  fun CreateConfigObjectFromYaml() {
    val configurationObjectYAML = ConfigurationProvider.setConfigType(ConfigurationProvider.ConfigSource.JSON)
        .getConfig()
    assertEquals(configurationObjectYAML.user, "moneyman")
    assertEquals(configurationObjectYAML.pass, 100500)
    assertEquals(configurationObjectYAML.host, "qa-delivery-master.mm.ru")
    assertEquals(configurationObjectYAML.registrationServiceEndpoint, "/client-area/registration")
  }
}