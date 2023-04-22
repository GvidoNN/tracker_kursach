package my.lovely.trucks.domain.model

data class RegistrationRequest(
    val login: String,
    val email: String,
    val password: String
)
