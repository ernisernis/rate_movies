package com.example.ratemovies.movie.data.repository

import android.database.sqlite.SQLiteException
import com.example.ratemovies.core.domain.util.DataError
import com.example.ratemovies.core.domain.util.EmptyResult
import com.example.ratemovies.core.domain.util.Result
import com.example.ratemovies.movie.data.database.RatingDao
import com.example.ratemovies.movie.data.mappers.toRating
import com.example.ratemovies.movie.domain.model.Rating
import com.example.ratemovies.movie.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProfileRepositoryImpl(
    private val ratingDao: RatingDao
): ProfileRepository {
    override fun getRatingsOrderedByCreatedTime(): Flow<List<Rating>> {
        return ratingDao
            .getRatingsOrderedByCreatedTime()
            .map { ratingEntities ->
                ratingEntities.map { it.toRating() }
            }
    }

    override suspend fun deleteRating(id: Int): EmptyResult<DataError.Local> {
        return try {
            ratingDao.deleteRating(id)
            ratingDao.deleteMovieEntity(id)
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }
}
