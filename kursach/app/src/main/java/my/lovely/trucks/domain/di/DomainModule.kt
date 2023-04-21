package my.lovely.trucks.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import my.lovely.trucks.domain.repository.GetDataRepository
import my.lovely.trucks.domain.usecase.GetDataUseCase


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetDataUseCase(getDataRepository: GetDataRepository): GetDataUseCase {
        return GetDataUseCase(getDataRepository = getDataRepository)
    }
}