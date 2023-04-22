package my.lovely.trucks.data.repository

import android.util.Log
import my.lovely.trucks.data.api.LoginService
import my.lovely.trucks.domain.model.LoginRequest
import my.lovely.trucks.domain.model.LoginResponse
import my.lovely.trucks.domain.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginService: LoginService): LoginRepository {

    override suspend fun loginRequest(login: String, password: String): Response<LoginResponse>? {
        return try{
            val result = loginService.sendPostLogin(loginModel = LoginRequest(login = login, password = password))
            result
        } catch (e: java.net.UnknownHostException){
            Log.d("MyLog",e.toString())
            null
        }
    }


}