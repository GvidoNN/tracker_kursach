package my.lovely.trucks.presentation.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.lovely.trucks.domain.model.RegistrationResponse
import my.lovely.trucks.domain.usecase.RequestRegistrationUseCase
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val requestRegistrationUseCase: RequestRegistrationUseCase) : ViewModel() {

    private var requestRegistrationLiveData = MutableLiveData<RegistrationResponse>()

    val registration: LiveData<RegistrationResponse>
        get() = requestRegistrationLiveData

    fun sendPostRegistration(login: String, password: String, email: String) = viewModelScope.launch(Dispatchers.IO) {
        try{
            var result = requestRegistrationUseCase.getRegistrationResponse(login = login, password = password, email= email)
            requestRegistrationLiveData.postValue(result?.body() ?: null)
            if (result != null) {
                Log.d("MyLog",result.raw().toString())
            }

        } catch (e: Exception){
            Log.d("MyLog",e.toString())
        }
    }
}