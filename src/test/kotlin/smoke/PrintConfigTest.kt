package smoke

import config.Configuration
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PrintConfigTest {
    @DisplayName("Print_Config_Object_From_Json")
    @Test
    fun PrintConfigObjectFromJson() {
        println(Configuration().createObjectFromJSON())
    }

    @DisplayName("Print_Config_Object_From_Yaml")
    @Test
    fun PrintConfigObjectFromYaml() {
        println(Configuration().createObjectFromYAML())
    }
}