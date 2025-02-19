package com.example.ratemovies.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object MovieGraph: Route

    @Serializable
    data object MovieList: Route

    @Serializable
    data class MovieDetail(val id: Int): Route

    @Serializable
    data object MovieRate: Route
}