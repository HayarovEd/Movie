package com.edurda77.movie.di

import com.edurda77.movie.network.ApiService
import com.edurda77.movie.network.RepositoryMovieImpl
import com.edurda77.movie.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {
    @Provides
    fun providesUrl() = BASE_URL

    @Provides
    @Singleton
    fun providesApiService(url: String): ApiService =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    @Provides
    @Singleton
    fun provideMainRemoteData(apiService: ApiService) : RepositoryMovieImpl = RepositoryMovieImpl(apiService)
}