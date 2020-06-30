package config

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Configuration(
    val user: String,
    val pass: Int,
    val host: String,
    val registrationServiceEndpoint: String,
    val privateAreaServiceEndpoint: String
)