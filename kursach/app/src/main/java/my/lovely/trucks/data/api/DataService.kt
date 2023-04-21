package my.lovely.trucks.data.api

import my.lovely.trucks.domain.model.DataResponse
import retrofit2.Response
import retrofit2.http.GET


interface DataService {

    @GET("hello")
    suspend fun getData() : Response<DataResponse>

}
