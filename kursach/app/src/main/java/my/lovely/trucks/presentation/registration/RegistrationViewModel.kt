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
    private var progressBarLiveData = MutableLiveData<Boolean>()

    val registration: LiveData<RegistrationResponse>
        get() = requestRegistrationLiveData

    val progressBar: LiveData<Boolean>
        get() = progressBarLiveData

    fun sendPostRegistration(login: String, password: String, email: String) = viewModelScope.launch(Dispatchers.IO) {
        try{
            progressBarLiveData.postValue(true)
            var result = requestRegistrationUseCase.getRegistrationResponse(login = login, password = password, email= email)
            requestRegistrationLiveData.postValue(result?.body() ?: null)
            if (result != null) {
                Log.d("MyLog",result.raw().toString())
            }
            progressBarLiveData.postValue(false)

        } catch (e: Exception){
            progressBarLiveData.postValue(false)
            Log.d("MyLog",e.toString())
        }
    }
}