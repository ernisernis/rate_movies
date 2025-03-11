package com.example.ratemovies.di

import com.example.ratemovies.movie.data.database.RatingDao
import com.example.ratemovies.movie.data.use_case.RateMovieUseCaseImpl
import com.example.ratemovies.movie.domain.use_case.RateMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideRateMovieUseCase(ratingDao: RatingDao): RateMovieUseCase {
        return RateMovieUseCaseImpl(ratingDao)
    }
}