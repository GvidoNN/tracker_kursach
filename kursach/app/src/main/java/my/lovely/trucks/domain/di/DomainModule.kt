package my.lovely.trucks.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import my.lovely.trucks.domain.repository.GetDataRepository
import my.lovely.trucks.domain.repository.LoginRepository
import my.lovely.trucks.domain.repository.RegistrationRepository
import my.lovely.trucks.domain.usecase.GetDataUseCase
import my.lovely.trucks.domain.usecase.RequestLoginUseCase
import my.lovely.trucks.domain.usecase.RequestRegistrationUseCase


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetDataUseCase(getDataRepository: GetDataRepository): GetDataUseCase {
        return GetDataUseCase(getDataRepository = getDataRepository)
    }

    @Provides
    fun provideRequestLoginUseCase(loginRepository: LoginRepository): RequestLoginUseCase {
        return RequestLoginUseCase(loginRepository = loginRepository)
    }

    @Provides
    fun provideRequestRegistrationUseCase(registrationRepository: RegistrationRepository): RequestRegistrationUseCase {
        return RequestRegistrationUseCase(registrationRepository = registrationRepository)
    }
}