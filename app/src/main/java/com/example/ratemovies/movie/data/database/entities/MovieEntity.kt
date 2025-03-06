package com.example.ratemovies.movie.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ratemovies.movie.data.dto.MovieDetailDto

@Entity
data class MovieEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val title: String,
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val movieDetailDto: MovieDetailDto?,
)