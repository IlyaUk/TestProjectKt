package config

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ConfigurationObject(
    val user: String,
    val pass: Int,
    val host: String,
    val registrationServiceEndpoint: String,
    @JsonProperty("numberPhone")
    val phoneNumber: Int
)