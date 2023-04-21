package lovely.truck.database.users

data class UserDTO(
    val login: String,
    val password: String,
    val email: String?,
    val username: String
)
