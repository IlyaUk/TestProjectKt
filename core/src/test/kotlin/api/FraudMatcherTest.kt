package api

import api.fraud.FraudData
import api.fraud.FraudTestData
import config.ConfigSource
import config.ConfigurationProvider
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.ResponseSpecification
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import utils.getClassObjectFromYaml
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FraudMatcherTest {
  private val config = ConfigurationProvider.setConfigType(ConfigSource.JSON).getConfig()
  private val testResourcesFilePath = "fraud/"
  private val testResourcesFile: List<String> = listOf("matcher")
  private val fraudMatcherOperations: FraudMatcherOperations = FraudMatcherOperations(config)

  @BeforeAll
  fun setupCrmConfiguration() {
    fraudMatcherOperations.getBaseCrmConfig()
  }

  @ParameterizedTest(name = "#{index} Fraud Matcher - Black List contains logic - {0}")
  @MethodSource("getTestData")
  fun `Send fraud data to fraud matcher`(
      case: String,
      fraudData: FraudData,
      fraudResponseDataExpected: FraudData
  ) {
    val fraudMatcherResponseData: FraudData = fraudMatcherOperations.sendPostRequestToFraudMatcher(fraudData)

    Assertions.assertEquals(fraudResponseDataExpected, fraudMatcherResponseData)
  }

  @Test
  @DisplayName("For demonstration purposes of Rest Assured response validation only")
  fun `Validate fraud matcher response with response specification`() {
    val fraudMatcherRequestData: FraudData = getClassObjectFromYaml("fraud/matcher-demo.yaml", FraudData::class.java)
    val fraudMatcherExpectedData: FraudData = getClassObjectFromYaml("fraud/matcher-demoRS.yaml", FraudData::class.java)
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

  private fun getTestData(): Stream<Arguments> {
    return fraudMatcherOperations.readTestData<FraudTestData>(testResourcesFilePath, testResourcesFile)
        .map { fraudTestData -> fraudTestData.cases?.stream()?.map { Arguments.of(it.case, it.request, it.response) } }
        .flatMap { it }
  }
}