package com.example.ratemovies.movie.domain.use_case

import com.example.ratemovies.core.domain.util.DataError
import com.example.ratemovies.core.domain.util.EmptyResult
import com.example.ratemovies.movie.domain.model.Movie
import com.example.ratemovies.movie.domain.model.Rating

interface RateMovieUseCase {
    suspend fun rateMovie(movie: Movie, rating: Rating): EmptyResult<DataError.Local>
}