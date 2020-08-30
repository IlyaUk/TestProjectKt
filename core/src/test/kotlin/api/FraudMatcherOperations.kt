package api

import api.fraud.FraudData
import config.ApplicationConfig
import httpclient.request.AuthorizeInCrmRequest
import io.restassured.RestAssured
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.http.ContentType
import io.restassured.http.Cookies
import io.restassured.response.Response
import io.restassured.specification.ResponseSpecification
import org.junit.jupiter.api.Assertions
import utils.convertObjectToJsonString
import utils.getClassObjectFromString

class FraudMatcherOperations(private val config: ApplicationConfig) {
  private lateinit var baseResponseSpec: ResponseSpecification
  private val expectedHttpStatusCode = 200
  private val expectedContentType = ContentType.JSON

  fun getBaseCrmConfig() {
    RestAssured.baseURI = "https://${config.host}/"
    RestAssured.authentication = RestAssured.basic(config.user, config.pass.toString())
    baseResponseSpec = ResponseSpecBuilder().apply {
      expectContentType(expectedContentType)
      expectStatusCode(expectedHttpStatusCode)
    }.build()
  }

  private fun getCrmCookies(): Cookies {
    val crmCookies =
        RestAssured.given()
            .contentType(expectedContentType)
            .`when`()
            .body(convertObjectToJsonString(AuthorizeInCrmRequest(config.crmLogin, config.crmPass, config.crmCaptcha)))
            .post(config.signInServiceEndpoint)
            .then()
            .statusCode(expectedHttpStatusCode)
            .extract()
            .response()
            .detailedCookies

    Assertions.assertTrue(crmCookies.getValue("AuthUser").isNotEmpty())
    return crmCookies
  }

  fun sendPostRequestToFraudMatcher(fraudRequestData: FraudData): FraudData {
    val fraudRequestPayload: String = convertObjectToJsonString(fraudRequestData)
    val fraudMatcherResponseData: String =
        RestAssured.given()
            .contentType(expectedContentType)
            .cookies(getCrmCookies())
            .`when`()
            .body(fraudRequestPayload)
            .post(config.blackListServiceEndpoint)
            .then()
            .spec(baseResponseSpec)
            .extract()
            .response()
            .body
            .asString()

    return getClassObjectFromString(fraudMatcherResponseData, FraudData::class.java)
  }

  fun sendRequestToFraudMatcher(fraudRequestData: FraudData): Response {
    val fraudRequestPayload: String = convertObjectToJsonString(fraudRequestData)

    return RestAssured.given()
        .contentType(expectedContentType)
        .cookies(getCrmCookies())
        .`when`()
        .body(fraudRequestPayload)
        .post(config.blackListServiceEndpoint)
  }
}