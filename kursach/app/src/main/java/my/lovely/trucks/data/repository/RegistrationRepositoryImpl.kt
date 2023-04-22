package my.lovely.trucks.data.repository

import android.util.Log
import my.lovely.trucks.data.api.RegistrationService
import my.lovely.trucks.domain.model.LoginRequest
import my.lovely.trucks.domain.model.RegistrationRequest
import my.lovely.trucks.domain.model.RegistrationResponse
import my.lovely.trucks.domain.repository.RegistrationRepository
import retrofit2.Response
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(private val registrationService: RegistrationService): RegistrationRepository {

    override suspend fun passwordRequest(login: String, password: String, email: String): Response<RegistrationResponse>? {
        return try{
            val result = registrationService.sendPostRegistration(registrationModel = RegistrationRequest(login = login, password = password, email = email))
            result
        } catch (e: java.net.UnknownHostException){
            Log.d("MyLog",e.toString())
            null
        }
    }
}