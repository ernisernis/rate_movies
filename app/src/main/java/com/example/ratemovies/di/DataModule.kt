package com.example.ratemovies.di

import android.content.Context
import androidx.room.Room
import com.example.ratemovies.core.data.networking.HttpClientFactory
import com.example.ratemovies.movie.data.database.MovieDatabase
import com.example.ratemovies.movie.data.data_source.MovieDataSourceImpl
import com.example.ratemovies.movie.data.database.BookmarkMovieDao
import com.example.ratemovies.movie.domain.data_source.MovieDataSource
import com.example.ratemovies.movie.data.repository.MovieRepositoryImpl
import com.example.ratemovies.movie.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            MovieDatabase.DB_NAME,
        ).build()
    }

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
    fun provideKtorRemoteMovieDataSource(httpClient: HttpClient): MovieDataSource {
        return MovieDataSourceImpl(httpClient)
    }

    @Provides
    @Singleton
    fun provideBookmarkMovieDao(database: MovieDatabase): BookmarkMovieDao {
        return database.bookmarkMovieDao
    }

    @Provides
    @Singleton
    fun provideDefaultMovieRepository(remoteMovieDataSource: MovieDataSource, bookmarkMovieDao: BookmarkMovieDao): MovieRepository {
        return MovieRepositoryImpl(remoteMovieDataSource, bookmarkMovieDao)
    }
}