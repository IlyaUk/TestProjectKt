class Configuration : ConfigurationProvider() {
    data class ConfigurationObject(
        val user: String,
        val pass: Int,
        val host: String,
        val registrationServiceEndpoint: String
    )

    //val configurationObjectJSON: ConfigurationObject = ConfigurationProvider().deserializeObjectJSON()
    //val configurationObjectYAML: ConfigurationObject = ConfigurationProvider().deserializeObjectYAML()

    fun createObjectFromJSON(): ConfigurationObject {
        return ConfigurationProvider().deserializeObjectJSON()
    }

    fun createObjectFromYAML(): ConfigurationObject {
        return ConfigurationProvider().deserializeObjectYAML()
    }
}

fun main() {
    /*println(Configuration().configurationObjectJSON)
    println(Configuration().configurationObjectYAML)*/
    println(Configuration().createObjectFromJSON())
    println(Configuration().createObjectFromYAML())
}