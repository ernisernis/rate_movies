package com.example.ratemovies.movie.presentation.movie_details

import com.example.ratemovies.movie.presentation.models.MovieDetailsUi

data class MovieDetailsState(
    val bannerUrl: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val movieDetailsUi: MovieDetailsUi? = null,
)
