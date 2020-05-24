import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import java.io.File
import com.fasterxml.jackson.module.kotlin.*

open class ConfigurationProvider {
    private val fileNameJSON = "src/main/resources/BaseConfigurationJSON"
    private val fileNameYAML = "src/main/resources/BaseConfigurationYAML"
    private val objectMapperJSON = ObjectMapper().registerModule(KotlinModule())
    private val objectMapperYAML = ObjectMapper(YAMLFactory()).registerModule(KotlinModule())

    private fun readJSONFileWithBufferedReader(): String {
        return File(fileNameJSON).bufferedReader().readText()
    }
    private fun readYAMLFileWithBufferedReader(): String {
        return File(fileNameYAML).bufferedReader().readText()
    }

    fun deserializeObjectJSON(): Configuration.ConfigurationObject {
        val stringFromJSON: String = ConfigurationProvider().readJSONFileWithBufferedReader()
        return objectMapperJSON.readValue(stringFromJSON)
    }
    fun deserializeObjectYAML(): Configuration.ConfigurationObject {
        val stringFromYAML: String = ConfigurationProvider().readYAMLFileWithBufferedReader()
        return objectMapperYAML.readValue(stringFromYAML)
    }
}