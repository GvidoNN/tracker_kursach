package my.lovely.trucks.domain.usecase

import android.util.Log
import my.lovely.trucks.domain.model.LoginResponse
import my.lovely.trucks.domain.model.RegistrationResponse
import my.lovely.trucks.domain.repository.LoginRepository
import my.lovely.trucks.domain.repository.RegistrationRepository
import retrofit2.Response
import javax.inject.Inject

class RequestRegistrationUseCase @Inject constructor(private val registrationRepository: RegistrationRepository) {

    suspend fun getRegistrationResponse(login: String, password: String, email: String): Response<RegistrationResponse>? {
        val result = registrationRepository.passwordRequest(login = login, password = password, email = email)
        Log.d("MyLog", "В UseCase reg ${result?.body() ?: null}")
        return result
    }
}