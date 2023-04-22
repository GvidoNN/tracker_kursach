package my.lovely.trucks.domain.repository

import my.lovely.trucks.domain.model.LoginResponse
import my.lovely.trucks.domain.model.RegistrationResponse
import retrofit2.Response

interface RegistrationRepository {

    suspend fun passwordRequest(login: String, password: String, email: String) : Response<RegistrationResponse>?
}