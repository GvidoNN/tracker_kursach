package my.lovely.trucks.domain.usecase

import android.util.Log
import my.lovely.trucks.domain.model.DataResponse
import my.lovely.trucks.domain.model.LoginResponse
import my.lovely.trucks.domain.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class RequestLoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend fun getLoginResponse(login: String, password: String): Response<LoginResponse>? {
        val result = loginRepository.loginRequest(login = login, password = password)
        Log.d("MyLog","В UseCase ${result?.body() ?: null}")
        return result
    }

}