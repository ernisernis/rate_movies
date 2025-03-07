package com.example.ratemovies.movie.domain.repository

import com.example.ratemovies.core.domain.util.DataError
import com.example.ratemovies.core.domain.util.Result
import com.example.ratemovies.movie.domain.model.Movie
import com.example.ratemovies.movie.domain.model.MovieDetail

interface MovieRepository {
    suspend fun getUpcomingMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getTopRatedMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getPopularMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getNowPlayingMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getMovieDetail(id: Int): Result<MovieDetail, DataError.Remote>
}