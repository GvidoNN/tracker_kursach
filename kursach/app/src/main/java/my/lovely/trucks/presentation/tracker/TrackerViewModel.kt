package my.lovely.trucks.presentation.tracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.lovely.trucks.data.api.LoginService
import my.lovely.trucks.domain.model.DataResponse
import my.lovely.trucks.domain.model.LoginRequest
import my.lovely.trucks.domain.model.LoginResponse
import my.lovely.trucks.domain.usecase.GetDataUseCase
import javax.inject.Inject

@HiltViewModel
class TrackerViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
) : ViewModel() {

    private val dataResponseLiveData = MutableLiveData<DataResponse>()
    private var progressBarLiveData = MutableLiveData<Boolean>()

    val dataResponse: LiveData<DataResponse>
        get() = dataResponseLiveData

    val progressBar: LiveData<Boolean>
        get() = progressBarLiveData

    fun dataResponce() = viewModelScope.launch(Dispatchers.IO) {
        var result = getDataUseCase.getSearchInside()
        dataResponseLiveData.postValue(result?.body() ?: null)
    }


}