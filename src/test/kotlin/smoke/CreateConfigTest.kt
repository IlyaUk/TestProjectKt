package smoke

import config.Configuration
import config.ConfigurationObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CreateConfigTest {
    @DisplayName("Create_Config_Object_From_Json")
    @Test
    fun CreateConfigObjectFromJson() {
        val configurationObjectJSON: ConfigurationObject = Configuration().createObjectFromJSON()
        assertEquals(configurationObjectJSON.user, "moneyman")
        assertEquals(configurationObjectJSON.pass, 100500)
        assertEquals(configurationObjectJSON.host, "qa-delivery-master.mm.ru")
        assertEquals(configurationObjectJSON.registrationServiceEndpoint, "/client-area/registration")
    }

    @DisplayName("Create_Config_Object_From_Yaml")
    @Test
    fun CreateConfigObjectFromYaml() {
        val configurationObjectYAML: ConfigurationObject = Configuration().createObjectFromYAML()
        assertEquals(configurationObjectYAML.user, "moneyman")
        assertEquals(configurationObjectYAML.pass, 100500)
        assertEquals(configurationObjectYAML.host, "qa-delivery-master.mm.ru")
        assertEquals(configurationObjectYAML.registrationServiceEndpoint, "/client-area/registration")
    }
}