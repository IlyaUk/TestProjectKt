package api

import api.fraud.FraudData
import config.ConfigSource
import config.ConfigurationProvider
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.ResponseSpecification
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.*
import utils.getClassObjectFromYaml

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FraudMatcherTest {
  private val config = ConfigurationProvider.setConfigType(ConfigSource.JSON).getConfig()
  private val testResourcesFilePath = "fraud/matcher-double.yaml"
  private val testResourcesFilePathResponse = "fraud/matcher-doubleRS.yaml"
  private val fraudMatcherOperations: FraudMatcherOperations = FraudMatcherOperations(config)

  @BeforeAll
  fun setupCrmConfiguration() {
    fraudMatcherOperations.getBaseCrmConfig()
  }

  @Test
  fun `Send fraud data to fraud matcher`() {
    val fraudMatcherRequestData: FraudData = getClassObjectFromYaml(testResourcesFilePath, FraudData::class.java)
    val fraudMatcherExpectedData: FraudData = getClassObjectFromYaml(testResourcesFilePathResponse, FraudData::class.java)
    val fraudMatcherResponseData: FraudData = fraudMatcherOperations.sendPostRequestToFraudMatcher(fraudMatcherRequestData)

    Assertions.assertEquals(fraudMatcherExpectedData, fraudMatcherResponseData)
  }

  @Test
  @DisplayName("For demonstration purposes of Rest Assured response validation only")
  fun `Validate fraud matcher response with response specification`() {
    val fraudMatcherRequestData: FraudData = getClassObjectFromYaml(testResourcesFilePath, FraudData::class.java)
    val fraudMatcherExpectedData: FraudData = getClassObjectFromYaml(testResourcesFilePathResponse, FraudData::class.java)
    val responseSpec: ResponseSpecification = ResponseSpecBuilder().apply {
      expectContentType(ContentType.JSON)
      expectStatusCode(200)
      expectBody("FULL_NAME", equalTo(fraudMatcherExpectedData.fullName))
      expectBody("SECOND_LAST_NAME", equalTo(fraudMatcherExpectedData.secondLastName))
      expectBody("EMAIL", equalTo(fraudMatcherExpectedData.email))
      expectBody("PHONE_MOBILE", equalTo(fraudMatcherExpectedData.phoneMobile))
      expectBody("COMPANY_NAME", equalTo(fraudMatcherExpectedData.companyName))
      expectBody("PHONE_WORK", equalTo(fraudMatcherExpectedData.phoneWork))
      expectBody("REFERENCE_CONTACT_PHONE", equalTo(fraudMatcherExpectedData.referenceContactPhone))
      expectBody("IP", equalTo(fraudMatcherExpectedData.ip))
      expectBody("PHONE_HOME", equalTo(fraudMatcherExpectedData.phoneHome))
      expectBody("ADDRESS_REG", equalTo(fraudMatcherExpectedData.addressReg))
      expectBody("DEVICE_ALIAS", equalTo(fraudMatcherExpectedData.deviceAlias))
      expectBody("CURP", equalTo(fraudMatcherExpectedData.curp))
      expectBody("RFC", equalTo(fraudMatcherExpectedData.rfc))
      expectBody("CLABE", equalTo(fraudMatcherExpectedData.clabe))
      expectBody("DEBIT_CARD", equalTo(fraudMatcherExpectedData.debitCard))
    }.build()

    val fraudMatcherResponseData: Response = fraudMatcherOperations.sendRequestToFraudMatcher(fraudMatcherRequestData)
    fraudMatcherResponseData
        .then()
        .spec(responseSpec)
  }
}