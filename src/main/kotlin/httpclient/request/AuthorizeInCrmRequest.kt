package httpclient.request

import httpclient.utils.SerializableRequest

data class AuthorizeInCrmRequest(
    val login: String,
    val password: String,
    val captcha: String
) : SerializableRequest
