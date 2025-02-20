package com.example.ratemovies.di

import com.example.ratemovies.core.data.networking.HttpClientFactory
import com.example.ratemovies.movie.data.network.KtorRemoteMovieDataSource
import com.example.ratemovies.movie.data.network.RemoteMovieDataSource
import com.example.ratemovies.movie.data.repository.DefaultMovieRepository
import com.example.ratemovies.movie.domain.MovieRepository
import com.example.ratemovies.movie.presentation.models.use_case.ValidateRateDescription
import com.example.ratemovies.movie.presentation.models.use_case.ValidateRateNumber
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClientEngine(): HttpClientEngine {
        return OkHttp.create()
    }

    @Provides
    @Singleton
    fun provideHttpClientFactory(engine: HttpClientEngine): HttpClient {
        return HttpClientFactory.create(engine)
    }

    @Provides
    @Singleton
    fun provideKtorRemoteMovieDataSource(httpClient: HttpClient): RemoteMovieDataSource {
        return KtorRemoteMovieDataSource(httpClient)
    }

    @Provides
    @Singleton
    fun provideDefaultMovieRepository(remoteMovieDataSource: RemoteMovieDataSource): MovieRepository {
        return DefaultMovieRepository(remoteMovieDataSource)
    }

    @Provides
    @Singleton
    fun provideValidateRateDescription(): ValidateRateDescription {
        return ValidateRateDescription()
    }

    @Provides
    @Singleton
    fun provideValidateRateNumber(): ValidateRateNumber {
        return ValidateRateNumber()
    }
}