package com.example.ratemovies.movie.presentation.movie_profile

import com.example.ratemovies.movie.presentation.models.RatingUi

data class MovieProfileState(
    val ratingsUi: List<RatingUi> = listOf()
)
