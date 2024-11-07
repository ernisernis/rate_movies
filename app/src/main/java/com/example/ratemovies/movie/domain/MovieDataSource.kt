package com.example.ratemovies.movie.domain

import com.example.ratemovies.core.domain.util.NetworkError
import com.example.ratemovies.core.domain.util.Result

interface MovieDataSource {
    suspend fun getMovies(): Result<List<Movie>, NetworkError>
}