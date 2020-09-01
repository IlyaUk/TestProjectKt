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
import utils.getClassObjectFromYaml
import java.util.stream.Stream

class FraudMatcherOperations(private val config: ApplicationConfig) {
  private lateinit var baseResponseSpec: ResponseSpecification
  private lateinit var crmCookies: Cookies
  private val expectedHttpStatusCode = 200
  private val expectedContentType = ContentType.JSON

  fun getBaseCrmConfig() {
    RestAssured.baseURI = "https://${config.host}/"
    RestAssured.authentication = RestAssured.basic(config.user, config.pass.toString())
    baseResponseSpec = ResponseSpecBuilder().apply {
      expectContentType(expectedContentType)
      expectStatusCode(expectedHttpStatusCode)
    }.build()
    crmCookies = getCrmCookies()
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
    return RestAssured.given()
        .contentType(expectedContentType)
        .cookies(crmCookies)
        .`when`()
        .body(fraudRequestPayload)
        .post(config.blackListServiceEndpoint)
        .then()
        .spec(baseResponseSpec)
        .extract()
        .body()
        .`as`(FraudData::class.java)
  }

  fun sendRequestToFraudMatcher(fraudRequestData: FraudData): Response {
    val fraudRequestPayload: String = convertObjectToJsonString(fraudRequestData)

    return RestAssured.given()
        .contentType(expectedContentType)
        .cookies(crmCookies)
        .`when`()
        .body(fraudRequestPayload)
        .post(config.blackListServiceEndpoint)
  }

  inline fun <reified T> readTestData(testResourcePath: String, testResourceFiles: List<String>): Stream<T> {
    return testResourceFiles.stream()
        .map { testResourceFile ->
          val filePath = "$testResourcePath/$testResourceFile.yaml"
          getClassObjectFromYaml(filePath, T::class.java)
        }
  }
}