package my.lovely.trucks.domain.repository

import my.lovely.trucks.domain.model.LoginResponse
import retrofit2.Response

interface LoginRepository {

    suspend fun loginRequest(login: String, password: String) : Response<LoginResponse>?
}