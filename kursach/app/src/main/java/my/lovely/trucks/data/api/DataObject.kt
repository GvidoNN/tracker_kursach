package my.lovely.trucks.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "http://10.0.2.2:8080/"

@Module
@InstallIn(SingletonComponent::class)
object DataObject {

    var retrofitService: DataService? = null
    var retrofitLoginServise: LoginService? = null
    var retrofitRegistrationService: RegistrationService? = null
    var retrofitInfoTrackerService: TrackerService? = null

    @Provides
    @Singleton
    fun getInstance(): DataService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        if (retrofitService == null) {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(DataService::class.java)
        }
        return retrofitService!!
    }

    @Provides
    @Singleton
    fun postLogin(): LoginService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        if (retrofitLoginServise == null) {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitLoginServise = retrofit.create(LoginService::class.java)
        }
        return retrofitLoginServise!!
    }

    @Provides
    @Singleton
    fun postRegistration(): RegistrationService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        if (retrofitRegistrationService == null) {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitRegistrationService = retrofit.create(RegistrationService::class.java)
        }
        return retrofitRegistrationService!!
    }

    @Provides
    @Singleton
    fun postInfoTracker(): TrackerService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        if (retrofitInfoTrackerService == null) {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitInfoTrackerService = retrofit.create(TrackerService::class.java)
        }
        return retrofitInfoTrackerService!!
    }
}