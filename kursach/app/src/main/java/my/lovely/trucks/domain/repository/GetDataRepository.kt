package my.lovely.trucks.domain.repository

import my.lovely.trucks.domain.model.DataResponse
import retrofit2.Response

interface GetDataRepository {

    suspend fun getData(): Response<DataResponse>?
}