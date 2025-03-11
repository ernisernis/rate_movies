package com.example.ratemovies.movie.domain.repository

import com.example.ratemovies.core.domain.util.DataError
import com.example.ratemovies.core.domain.util.EmptyResult
import com.example.ratemovies.core.domain.util.Result
import com.example.ratemovies.movie.domain.model.BookmarkMovie
import com.example.ratemovies.movie.domain.model.Movie
import com.example.ratemovies.movie.domain.model.MovieDetail
import com.example.ratemovies.movie.domain.model.Rating
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    // Api
    suspend fun getUpcomingMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getTopRatedMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getPopularMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getNowPlayingMovies(): Result<List<Movie>, DataError.Remote>
    suspend fun getMovieDetail(id: Int): Result<MovieDetail, DataError.Remote>

    // Local
    suspend fun getRating(id: Int): Result<Rating, DataError.Local>
    suspend fun getMovie(id: Int): Result<Movie, DataError.Local>
    suspend fun deleteFromBookmark(id: Int)
    suspend fun markAsBookmarked(movie: Movie): EmptyResult<DataError.Local>
    suspend fun rateMovie(movie: Movie, rating: Rating): EmptyResult<DataError.Local>
    fun getBookmarks(): Flow<List<BookmarkMovie>>
    fun isBookBookmarked(id: Int): Flow<Boolean>
}