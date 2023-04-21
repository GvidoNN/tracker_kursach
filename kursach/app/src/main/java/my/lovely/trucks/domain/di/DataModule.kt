package my.lovely.trucks.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.lovely.trucks.data.api.DataService
import my.lovely.trucks.data.repository.GetDataRepositoryImpl
import my.lovely.trucks.domain.repository.GetDataRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideGetDataRepositoryImpl(dataService: DataService) : GetDataRepository {
        return GetDataRepositoryImpl(dataService = dataService)

    }
}