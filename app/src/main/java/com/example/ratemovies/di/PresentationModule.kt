package com.example.ratemovies.di

import com.example.ratemovies.movie.presentation.models.use_case.ValidateRateDescription
import com.example.ratemovies.movie.presentation.models.use_case.ValidateRateNumber
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {
    @Provides
    fun provideValidateRateDescription(): ValidateRateDescription {
        return ValidateRateDescription()
    }

    @Provides
    fun provideValidateRateNumber(): ValidateRateNumber {
        return ValidateRateNumber()
    }
}