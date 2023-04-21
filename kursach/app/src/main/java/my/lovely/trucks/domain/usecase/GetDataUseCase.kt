package my.lovely.trucks.domain.usecase

import my.lovely.trucks.domain.model.DataResponse
import my.lovely.trucks.domain.repository.GetDataRepository
import retrofit2.Response
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val getDataRepository: GetDataRepository) {

    suspend fun getSearchInside(): Response<DataResponse>? {
        val result = getDataRepository.getData()
        return result
    }
}