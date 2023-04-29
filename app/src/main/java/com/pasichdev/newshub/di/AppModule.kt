package com.pasichdev.newshub.di

import com.pasichdev.newshub.data.repository.NewsRepository
import com.pasichdev.newshub.data.repository.NewsRepositoryImpl
import com.pasichdev.newshub.data.services.ApiService
import com.pasichdev.newshub.utils.BaseUrlApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModules {
    @Provides
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
        return Retrofit.Builder()
            .baseUrl(BaseUrlApi)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideGamesService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideGamesRepository(
        apiService: ApiService,
    ): NewsRepository = NewsRepositoryImpl(
        apiService = apiService,
    )
}