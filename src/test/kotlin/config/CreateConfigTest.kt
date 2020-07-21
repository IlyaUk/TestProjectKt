package config

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class CreateConfigTest {
    @DisplayName("Create_Config_Object_From_Json")
    @Test
    fun createConfigObjectFromJson() {
        val configurationFromJsonFile = ConfigurationProvider.setConfigType(ConfigSource.JSON).getConfig()
        assertAll("configurationObjectJSON",
            { assertEquals("moneyman", configurationFromJsonFile.user) },
            { assertEquals(1005, configurationFromJsonFile.pass) },
            { assertEquals("qa-delivery-mx-master.moneyman.ru", configurationFromJsonFile.host) },
            { assertEquals("client-area/registration", configurationFromJsonFile.registrationServiceEndpoint) },
            { assertEquals("private-area/static/#/login", configurationFromJsonFile.privateAreaServiceEndpoint) },
            { assertEquals("/", configurationFromJsonFile.landingPageServiceEndpoint) },
            { assertEquals("1", configurationFromJsonFile.dbUrl) },
            { assertEquals("2", configurationFromJsonFile.dbUser) },
            { assertEquals("3", configurationFromJsonFile.dbPassword) },
            { assertEquals("4", configurationFromJsonFile.dbSchema) }
        )
    }

    @DisplayName("Create_Config_Object_From_Yaml")
    @Test
    fun createConfigObjectFromYaml() {
        val configurationFromYamlFile = ConfigurationProvider.setConfigType(ConfigSource.YAML).getConfig()
        assertAll("configurationObjectYAML",
            { assertEquals("moneyman", configurationFromYamlFile.user) },
            { assertEquals(1005, configurationFromYamlFile.pass) },
            { assertEquals("qa-delivery-mx-master.moneyman.ru", configurationFromYamlFile.host) },
            { assertEquals("client-area/registration", configurationFromYamlFile.registrationServiceEndpoint) },
            { assertEquals("private-area/static/#/login", configurationFromYamlFile.privateAreaServiceEndpoint) },
            { assertEquals("/", configurationFromYamlFile.landingPageServiceEndpoint) },
            { assertEquals("/", configurationFromYamlFile.landingPageServiceEndpoint) },
            { assertEquals("1", configurationFromYamlFile.dbUrl) },
            { assertEquals("2", configurationFromYamlFile.dbUser) },
            { assertEquals("3", configurationFromYamlFile.dbPassword) },
            { assertEquals("4", configurationFromYamlFile.dbSchema) }
        )
    }
}