package config

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApplicationConfig(
    val user: String,
    val pass: Int,
    val host: String,
    val registrationServiceEndpoint: String,
    val privateAreaServiceEndpoint: String,
    var landingPageServiceEndpoint: String
){
  fun getURLWithAuthorization() = "https://$user:$pass@$host/"
  fun getHostURL() = "https://$host/"
}