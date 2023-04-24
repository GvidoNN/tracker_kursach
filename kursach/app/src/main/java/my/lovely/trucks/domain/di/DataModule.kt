package my.lovely.trucks.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.lovely.trucks.data.api.DataService
import my.lovely.trucks.data.api.LoginService
import my.lovely.trucks.data.api.RegistrationService
import my.lovely.trucks.data.api.TrackerService
import my.lovely.trucks.data.repository.GetDataRepositoryImpl
import my.lovely.trucks.data.repository.LoginRepositoryImpl
import my.lovely.trucks.data.repository.RegistrationRepositoryImpl
import my.lovely.trucks.data.repository.TrackerRepositoryImpl
import my.lovely.trucks.domain.repository.GetDataRepository
import my.lovely.trucks.domain.repository.LoginRepository
import my.lovely.trucks.domain.repository.RegistrationRepository
import my.lovely.trucks.domain.repository.TrackerRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideGetDataRepositoryImpl(dataService: DataService) : GetDataRepository {
        return GetDataRepositoryImpl(dataService = dataService)

    }

    @Provides
    @Singleton
    fun provideLoginRepositoryImpl(loginService: LoginService) : LoginRepository {
        return LoginRepositoryImpl(loginService = loginService)
    }

    @Provides
    @Singleton
    fun provideRegistrationRepositoryImpl(registrationService: RegistrationService) : RegistrationRepository {
        return RegistrationRepositoryImpl(registrationService = registrationService)
    }

    @Provides
    @Singleton
    fun provideTrackerRepositoryImpl(trackerService: TrackerService) : TrackerRepository {
        return TrackerRepositoryImpl(trackerService = trackerService)
    }

}