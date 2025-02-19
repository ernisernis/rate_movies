package com.example.ratemovies.di

import com.example.ratemovies.core.data.networking.HttpClientFactory
import com.example.ratemovies.movie.data.network.KtorRemoteMovieDataSource
import com.example.ratemovies.movie.data.network.RemoteMovieDataSource
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
}