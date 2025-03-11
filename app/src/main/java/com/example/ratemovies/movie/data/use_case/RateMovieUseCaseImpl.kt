package com.example.ratemovies.movie.data.use_case

import android.database.sqlite.SQLiteException
import com.example.ratemovies.core.domain.util.DataError
import com.example.ratemovies.core.domain.util.EmptyResult
import com.example.ratemovies.core.domain.util.Result
import com.example.ratemovies.movie.data.database.RatingDao
import com.example.ratemovies.movie.data.mappers.toMovieEntity
import com.example.ratemovies.movie.data.mappers.toRatingEntity
import com.example.ratemovies.movie.domain.model.Movie
import com.example.ratemovies.movie.domain.model.Rating
import com.example.ratemovies.movie.domain.use_case.RateMovieUseCase

class RateMovieUseCaseImpl(
    private val ratingDao: RatingDao,
): RateMovieUseCase {
    override suspend fun rateMovie(movie: Movie, rating: Rating): EmptyResult<DataError.Local> {
        return try {
            ratingDao.upsertMovieEntity(movie.toMovieEntity())
            ratingDao.upsertRatingEntity(rating.toRatingEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }
}