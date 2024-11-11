package com.example.ratemovies.core.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object MoviesGraph : Destination

    @Serializable
    data object MoviesScreen : Destination

    @Serializable
    data class DetailScreen(val id: String) : Destination
}