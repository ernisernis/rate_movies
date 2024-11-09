package com.example.ratemovies.movie.data

import com.example.ratemovies.BuildConfig
import com.example.ratemovies.core.data.networking.constructUrl
import com.example.ratemovies.core.data.networking.safeCall
import com.example.ratemovies.core.domain.util.NetworkError
import com.example.ratemovies.core.domain.util.Result
import com.example.ratemovies.core.domain.util.map
import com.example.ratemovies.movie.data.mappers.toMovie
import com.example.ratemovies.movie.data.networking.dto.MoviesResponseDto
import com.example.ratemovies.movie.domain.Movie
import com.example.ratemovies.movie.domain.MovieDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter


class RemoteMovieDataSource(
    private val httpClient: HttpClient
): MovieDataSource {
    override suspend fun getMovies(): Result<List<Movie>, NetworkError> {
       return safeCall<MoviesResponseDto>{
          httpClient.get(
                urlString = constructUrl("/movie/now_playing")
          ) {
              parameter("api_key", BuildConfig.API_KEY)
              parameter("include_adult", true)
          }
       }.map { response ->
          response.results.map { it.toMovie() }
       }
    }
}