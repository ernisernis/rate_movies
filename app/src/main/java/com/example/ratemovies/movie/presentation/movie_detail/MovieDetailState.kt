package com.example.ratemovies.movie.presentation.movie_detail

import com.example.ratemovies.movie.presentation.models.MovieDetailsUi

data class MovieDetailState(
    val bannerUrl: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val movieDetailsUi: MovieDetailsUi? = null,
)
