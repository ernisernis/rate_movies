package com.example.ratemovies.movie.data.data_source

import com.example.ratemovies.BuildConfig
import com.example.ratemovies.core.data.networking.constructUrl
import com.example.ratemovies.core.data.networking.safeCall
import com.example.ratemovies.core.domain.util.DataError
import com.example.ratemovies.core.domain.util.Result
import com.example.ratemovies.core.domain.util.map
import com.example.ratemovies.movie.data.dto.MovieDetailDto
import com.example.ratemovies.movie.data.dto.MovieResponseDto
import com.example.ratemovies.movie.domain.data_source.MovieDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieDataSourceImpl(
    private val httpClient: HttpClient,
) : MovieDataSource {
    override suspend fun getUpcomingMovies(): Result<MovieResponseDto, DataError.Remote> {
        return safeCall<MovieResponseDto> {
            httpClient.get(
                urlString = constructUrl("/movie/upcoming"),
            ) {
                parameter("api_key", BuildConfig.API_KEY)
                parameter("include_adult", true)
            }
        }.map { response ->
            response
        }
    }

    override suspend fun getTopRatedMovies(): Result<MovieResponseDto, DataError.Remote> {
        return safeCall<MovieResponseDto> {
            httpClient.get(
                urlString = constructUrl("/movie/top_rated"),
            ) {
                parameter("api_key", BuildConfig.API_KEY)
                parameter("include_adult", true)
            }
        }.map { response ->
            response
        }
    }

    override suspend fun getPopularMovies(): Result<MovieResponseDto, DataError.Remote> {
        return safeCall<MovieResponseDto> {
            httpClient.get(
                urlString = constructUrl("/movie/popular"),
            ) {
                parameter("api_key", BuildConfig.API_KEY)
                parameter("include_adult", true)
            }
        }.map { response ->
            response
        }
    }

    override suspend fun getNowPlayingMovies(): Result<MovieResponseDto, DataError.Remote> {
        return safeCall<MovieResponseDto> {
            httpClient.get(
                urlString = constructUrl("/movie/now_playing"),
            ) {
                parameter("api_key", BuildConfig.API_KEY)
                parameter("include_adult", true)
            }
        }.map { response ->
            response
        }
    }

    override suspend fun getMovieDetail(id: Int): Result<MovieDetailDto, DataError.Remote> {
        return safeCall<MovieDetailDto> {
            httpClient.get(
                urlString = constructUrl("/movie/$id"),
            ) {
                parameter("api_key", BuildConfig.API_KEY)
                parameter("append_to_response", "credits")
            }
        }.map { response ->
            response
        }
    }
}