package com.example.ratemovies.movie.domain

import com.example.ratemovies.core.domain.util.DataError
import com.example.ratemovies.core.domain.util.Result

interface MovieRepository {
    suspend fun getUpcomingMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getTopRatedMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getPopularMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getNowPlayingMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getMovieDetail(id: Int): Result<MovieDetail, DataError.Remote>
}