package com.example.ratemovies.movie.domain

import com.example.ratemovies.core.domain.util.NetworkError
import com.example.ratemovies.core.domain.util.Result

interface MovieDataSource {
    suspend fun getUpcomingMovies(): Result<List<Movie>, NetworkError>

    suspend fun getTopRatedMovies(): Result<List<Movie>, NetworkError>

    suspend fun getPopularMovies(): Result<List<Movie>, NetworkError>

    suspend fun getNowPlayingMovies(): Result<List<Movie>, NetworkError>

    suspend fun getMovieDetails(id: Int): Result<MovieDetails, NetworkError>
}