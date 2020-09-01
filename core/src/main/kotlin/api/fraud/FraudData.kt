package api.fraud

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import httpclient.utils.SerializableRequest

data class FraudData(
    @JsonProperty("FULL_NAME")
    val fullName: List<String>?,
    @JsonProperty("SECOND_LAST_NAME")
    val secondLastName: List<String>?,
    @JsonProperty("EMAIL")
    val email: List<String>?,
    @JsonProperty("PHONE_MOBILE")
    val phoneMobile: List<String>?,
    @JsonProperty("COMPANY_NAME")
    val companyName: List<String>?,
    @JsonProperty("PHONE_WORK")
    val phoneWork: List<String>?,
    @JsonProperty("REFERENCE_CONTACT_PHONE")
    val referenceContactPhone: List<String>?,
    @JsonProperty("IP")
    val ip: List<String>?,
    @JsonProperty("PHONE_HOME")
    val phoneHome: List<String>?,
    @JsonProperty("ADDRESS_REG")
    val addressReg: List<String>?,
    @JsonProperty("DEVICE_ALIAS")
    val deviceAlias: List<String>?,
    @JsonProperty("CURP")
    val curp: List<String>?,
    @JsonProperty("RFC")
    val rfc: List<String>?,
    @JsonProperty("CLABE")
    val clabe: List<String>?,
    @JsonProperty("DEBIT_CARD")
    val debitCard: List<String>?
): SerializableRequest

data class FraudTestData(val cases: List<FraudTestCase>?)

data class FraudTestCase(
    var case: String,
    var request: FraudData,
    var response: FraudData
)