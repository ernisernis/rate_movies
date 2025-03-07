package com.example.ratemovies.movie.data.repository

import com.example.ratemovies.core.domain.util.DataError
import com.example.ratemovies.core.domain.util.Result
import com.example.ratemovies.core.domain.util.map
import com.example.ratemovies.movie.data.database.BookmarkMovieDao
import com.example.ratemovies.movie.data.mappers.toBookmarkMovie
import com.example.ratemovies.movie.data.mappers.toMovie
import com.example.ratemovies.movie.data.mappers.toMovieDetail
import com.example.ratemovies.movie.domain.data_source.MovieDataSource
import com.example.ratemovies.movie.domain.model.BookmarkMovie
import com.example.ratemovies.movie.domain.model.Movie
import com.example.ratemovies.movie.domain.model.MovieDetail
import com.example.ratemovies.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl(
    private val remoteMovieDataSource: MovieDataSource,
    private val bookmarkMovieDao: BookmarkMovieDao,
) : MovieRepository {
    override suspend fun getUpcomingMovies(): Result<List<Movie>, DataError.Remote> {
        return remoteMovieDataSource
            .getUpcomingMovies()
            .map { dto ->
               dto.results.map { it.toMovie() }
            }
    }

    override suspend fun getTopRatedMovies(): Result<List<Movie>, DataError.Remote> {
        return remoteMovieDataSource
            .getTopRatedMovies()
            .map { dto ->
               dto.results.map { it.toMovie() }
            }
    }

    override suspend fun getPopularMovies(): Result<List<Movie>, DataError.Remote> {
        return remoteMovieDataSource
            .getPopularMovies()
            .map { dto ->
                dto.results.map { it.toMovie() }
            }
    }

    override suspend fun getNowPlayingMovies(): Result<List<Movie>, DataError.Remote> {
        return remoteMovieDataSource
            .getNowPlayingMovies()
            .map { dto ->
                dto.results.map { it.toMovie() }
            }
    }

    override suspend fun getMovieDetail(id: Int): Result<MovieDetail, DataError.Remote> {
        return remoteMovieDataSource
            .getMovieDetail(id)
            .map { dto ->
               dto.toMovieDetail()
            }
    }

    override suspend fun deleteFromBookmark(id: Int) {
        bookmarkMovieDao.deleteBookmarkEntity(id)
        bookmarkMovieDao.deleteMovieEntity(id)
    }

    override suspend fun getBookmarks(): Flow<List<BookmarkMovie>> {
        return bookmarkMovieDao
            .getBookmarkMovies()
            .map { bookmarkEntities ->
               bookmarkEntities.map { it.toBookmarkMovie() }
            }
    }
}