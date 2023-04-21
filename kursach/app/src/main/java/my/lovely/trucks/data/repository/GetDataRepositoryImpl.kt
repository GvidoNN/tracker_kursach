package my.lovely.trucks.data.repository

import my.lovely.trucks.data.api.DataService
import my.lovely.trucks.domain.model.DataResponse
import my.lovely.trucks.domain.repository.GetDataRepository
import retrofit2.Response
import javax.inject.Inject

class GetDataRepositoryImpl @Inject constructor(private val dataService: DataService):
    GetDataRepository {
    override suspend fun getData(): Response<DataResponse>?{
        return try{
            val result = dataService.getData()
            result
        } catch (e: java.net.UnknownHostException){
            null
        }
    }
}