package api.fraud

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import httpclient.utils.SerializableRequest

data class FraudData(
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("FULL_NAME")
    val fullName: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("SECOND_LAST_NAME")
    val secondLastName: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("EMAIL")
    val email: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("PHONE_MOBILE")
    val phoneMobile: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("COMPANY_NAME")
    val companyName: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("PHONE_WORK")
    val phoneWork: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("REFERENCE_CONTACT_PHONE")
    val referenceContactPhone: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("IP")
    val ip: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("PHONE_HOME")
    val phoneHome: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("ADDRESS_REG")
    val addressReg: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("DEVICE_ALIAS")
    val deviceAlias: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("CURP")
    val curp: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("RFC")
    val rfc: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("CLABE")
    val clabe: List<String>?,
    @JsonFormat(with = [JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    @JsonProperty("DEBIT_CARD")
    val debitCard: List<String>?
): SerializableRequest