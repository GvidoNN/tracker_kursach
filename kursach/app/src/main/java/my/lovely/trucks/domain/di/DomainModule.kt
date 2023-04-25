package my.lovely.trucks.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import my.lovely.trucks.domain.repository.GetDataRepository
import my.lovely.trucks.domain.repository.LoginRepository
import my.lovely.trucks.domain.repository.RegistrationRepository
import my.lovely.trucks.domain.repository.TrackerRepository
import my.lovely.trucks.domain.usecase.*


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

    @Provides
    fun provideRequestInfoTrackerUseCase(trackerRepository: TrackerRepository): RequestInfoTrackerUseCase {
        return RequestInfoTrackerUseCase(trackerRepository = trackerRepository)
    }

    @Provides
    fun provideRequestAddTrackerUseCase(trackerRepository: TrackerRepository): RequestAddTrackerUseCase {
        return RequestAddTrackerUseCase(trackerRepository = trackerRepository)
    }

}