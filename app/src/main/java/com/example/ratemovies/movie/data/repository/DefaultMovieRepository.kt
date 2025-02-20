package com.example.ratemovies.movie.data.repository

import com.example.ratemovies.core.domain.util.DataError
import com.example.ratemovies.core.domain.util.Result
import com.example.ratemovies.core.domain.util.map
import com.example.ratemovies.movie.data.mappers.toMovie
import com.example.ratemovies.movie.data.mappers.toMovieDetail
import com.example.ratemovies.movie.data.network.RemoteMovieDataSource
import com.example.ratemovies.movie.domain.Movie
import com.example.ratemovies.movie.domain.MovieDetail
import com.example.ratemovies.movie.domain.MovieRepository

class DefaultMovieRepository(
    private val remoteMovieDataSource: RemoteMovieDataSource,
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
}