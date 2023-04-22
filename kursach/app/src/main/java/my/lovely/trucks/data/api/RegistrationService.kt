package my.lovely.trucks.data.api

import my.lovely.trucks.domain.model.RegistrationRequest
import my.lovely.trucks.domain.model.RegistrationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationService {

    @POST("/registration")
    suspend fun sendPostRegistration(@Body registrationModel: RegistrationRequest): Response<RegistrationResponse>
}