package my.lovely.trucks.presentation.tracker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.lovely.trucks.domain.model.TrackerInfoResponse
import my.lovely.trucks.domain.usecase.RequestInfoTrackerUseCase
import javax.inject.Inject

@HiltViewModel
class TrackerViewModel @Inject constructor(
    private val requestInfoTrackerUseCase: RequestInfoTrackerUseCase,
) : ViewModel() {

    private val infoTrackerLiveData = MutableLiveData<TrackerInfoResponse>()
    private var progressBarLiveData = MutableLiveData<Boolean>()

    val trackerInfoResponse: LiveData<TrackerInfoResponse>
        get() = infoTrackerLiveData

    val progressBar: LiveData<Boolean>
        get() = progressBarLiveData

    fun postTrackerInfo(trackNumber: String) = viewModelScope.launch(Dispatchers.IO) {
        try{
            progressBarLiveData.postValue(true)
            var result = requestInfoTrackerUseCase.getTrackerInfoResponse(trackNumber = trackNumber)
            infoTrackerLiveData.postValue(result?.body() ?: null)
            if (result != null) {
                Log.d("MyLog",result.raw().toString())
            }
            progressBarLiveData.postValue(false)
        } catch (e: Exception){
            Log.d("MyLog",e.toString())
            progressBarLiveData.postValue(false)
        }
    }


}