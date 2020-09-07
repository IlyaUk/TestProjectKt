package config

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApplicationConfig(
    val user: String,
    val pass: Int,
    val host: String,
    val registrationServiceEndpoint: String,
    val privateAreaServiceEndpoint: String,
    var landingPageServiceEndpoint: String,
    var blackListServiceEndpoint: String,
    var signInServiceEndpoint: String,
    var dbUrl: String,
    var dbUser: String,
    var dbPassword: String,
    var dbSchema: String,
    var crmLogin: String,
    var crmPass: String,
    var crmCaptcha: String,
    var wireMockHost: String,
    var wireMockPort: Int
){
  fun getURLWithAuthorization() = "https://$user:$pass@$host/"
  fun getHostURL() = "https://$host/"
}