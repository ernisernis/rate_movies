package com.example.ratemovies.movie.domain.data_source

import com.example.ratemovies.core.domain.util.DataError
import com.example.ratemovies.core.domain.util.Result
import com.example.ratemovies.movie.data.dto.MovieDetailDto
import com.example.ratemovies.movie.data.dto.MovieResponseDto

interface MovieDataSource {
    suspend fun getUpcomingMovies(): Result<MovieResponseDto, DataError.Remote>
    suspend fun getTopRatedMovies(): Result<MovieResponseDto, DataError.Remote>
    suspend fun getPopularMovies(): Result<MovieResponseDto, DataError.Remote>
    suspend fun getNowPlayingMovies(): Result<MovieResponseDto, DataError.Remote>
    suspend fun getMovieDetail(id: Int): Result<MovieDetailDto, DataError.Remote>
}