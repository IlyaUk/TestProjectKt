package config

class Configuration : ConfigurationProvider() {
    fun createObjectFromJSON(): ConfigurationObject {
        return ConfigurationProvider().getObjectFromJSONFile()
    }

    fun createObjectFromYAML(): ConfigurationObject {
        return ConfigurationProvider().getObjectFromYAMLFile()
    }
}