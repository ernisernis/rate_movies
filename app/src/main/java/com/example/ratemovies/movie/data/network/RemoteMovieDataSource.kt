package com.example.ratemovies.movie.data.network

import com.example.ratemovies.core.domain.util.NetworkError
import com.example.ratemovies.core.domain.util.Result
import com.example.ratemovies.movie.domain.Movie
import com.example.ratemovies.movie.domain.MovieDetails

interface RemoteMovieDataSource {
    suspend fun getUpcomingMovies(): Result<List<Movie>, NetworkError>

    suspend fun getTopRatedMovies(): Result<List<Movie>, NetworkError>

    suspend fun getPopularMovies(): Result<List<Movie>, NetworkError>

    suspend fun getNowPlayingMovies(): Result<List<Movie>, NetworkError>

    suspend fun getMovieDetails(id: Int): Result<MovieDetails, NetworkError>
}