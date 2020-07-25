package httpclient.response

data class AuthorizeInCrmResponse(
    val id: Int,
    val userName: String,
    val localizedRole: String,
    val roleId: Int,
    val error: String?
)
