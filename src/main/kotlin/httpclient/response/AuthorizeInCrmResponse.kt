package httpclient.response

import httpclient.utils.SerializableRequest

data class AuthorizeInCrmResponse(
    val id: Int,
    val userName: String,
    val localizedRole: String,
    val roleId: Int,
    val error: String?
) : SerializableRequest
