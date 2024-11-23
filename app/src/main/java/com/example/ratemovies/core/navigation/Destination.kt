package com.example.ratemovies.core.navigation

import com.example.ratemovies.movie.domain.MovieNavArgs
import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object MoviesGraph : Destination

    @Serializable
    data object MoviesScreen : Destination

    @Serializable
    data class DetailScreen(val movieNavArgs: MovieNavArgs) : Destination

    @Serializable
    data class RateScreen(val movieNavArgs: MovieNavArgs) : Destination
}