package my.lovely.trucks.domain.model

data class LoginResponse(
    val token: String,
    val logIn: Boolean,
    val exception: String
)
