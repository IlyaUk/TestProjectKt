package config

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CreateConfigTest {
    @DisplayName("Create_Config_Object_From_Json")
    @Test
    fun createConfigObjectFromJson() {
        val configurationObjectJSON = ConfigurationProvider.setConfigType(ConfigSource.JSON)
            .getConfig()
        assertAll("configurationObjectJSON",
            { assertEquals("moneyman", configurationObjectJSON.user) },
            { assertEquals(1005, configurationObjectJSON.pass) },
            { assertEquals("qa-delivery-mx-master.moneyman.ru", configurationObjectJSON.host) },
            { assertEquals("client-area/registration", configurationObjectJSON.registrationServiceEndpoint) },
            { assertEquals("private-area/static/#/login", configurationObjectJSON.privateAreaServiceEndpoint) }
        )
    }

    @DisplayName("Create_Config_Object_From_Yaml")
    @Test
    fun createConfigObjectFromYaml() {
        val configurationObjectYAML = ConfigurationProvider.setConfigType(ConfigSource.YAML)
            .getConfig()
        assertAll("configurationObjectYAML",
            { assertEquals("moneyman", configurationObjectYAML.user) },
            { assertEquals(1005, configurationObjectYAML.pass) },
            { assertEquals("qa-delivery-mx-master.moneyman.ru", configurationObjectYAML.host) },
            { assertEquals("client-area/registration", configurationObjectYAML.registrationServiceEndpoint) },
            { assertEquals("private-area/static/#/login", configurationObjectYAML.privateAreaServiceEndpoint) }
        )
    }
}