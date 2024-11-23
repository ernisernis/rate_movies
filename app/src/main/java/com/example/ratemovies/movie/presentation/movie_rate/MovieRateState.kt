package com.example.ratemovies.movie.presentation.movie_rate

data class MovieRateState(
    val bannerUrl: String = "",
    val title: String = "",
    val imageUrl: String = "",
    val selectedIndex: Int = 0,
)
