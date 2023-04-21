package my.lovely.trucks.data.api

import my.lovely.trucks.domain.model.DataResponse
import my.lovely.trucks.domain.model.LoginRequest
import my.lovely.trucks.domain.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    suspend fun sendPostLogin(@Body loginModel: LoginRequest): Response<LoginResponse>
}