package com.example.ratemovies.core.navigation

import com.example.ratemovies.movie.presentation.models.MovieUi
import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object MoviesGraph : Destination

    @Serializable
    data object MoviesScreen : Destination

    @Serializable
    data class DetailScreen(val movieUi: MovieUi) : Destination
}