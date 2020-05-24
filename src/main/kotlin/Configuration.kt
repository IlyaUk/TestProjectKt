class Configuration: ConfigurationProvider() {
    data class ConfigurationObject (val user: String, val pass: Int, val host: String, val registrationServiceEndpoint: String)

    fun createObjectFromJSON(): ConfigurationObject {
        return ConfigurationProvider().deserializeObjectJSON()
    }

    fun createObjectFromYAML(): ConfigurationObject {
        return ConfigurationProvider().deserializeObjectYAML()
    }
}
fun main() {
    println(Configuration().createObjectFromJSON())
    println(Configuration().createObjectFromYAML())
}