package my.lovely.trucks.domain.model

data class RegistrationResponse(
    val token: String,
    val signUp: Boolean,
    val exception: String
)

