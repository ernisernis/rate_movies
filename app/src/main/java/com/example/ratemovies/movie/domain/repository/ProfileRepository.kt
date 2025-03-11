package com.example.ratemovies.movie.domain.repository

import com.example.ratemovies.core.domain.util.DataError
import com.example.ratemovies.core.domain.util.EmptyResult
import com.example.ratemovies.movie.domain.model.Rating
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getRatingsOrderedByCreatedTime(): Flow<List<Rating>>
    suspend fun deleteRating(id: Int): EmptyResult<DataError.Local>
}