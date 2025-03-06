package com.example.ratemovies.movie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ratemovies.movie.data.database.converters.IntListTypeConverter
import com.example.ratemovies.movie.data.database.converters.MovieTypeConverter
import com.example.ratemovies.movie.data.database.entities.BookmarkEntity
import com.example.ratemovies.movie.data.database.entities.MovieEntity
import com.example.ratemovies.movie.data.database.entities.RatingEntity

@Database(
    entities = [
        MovieEntity::class,
        BookmarkEntity::class,
        RatingEntity::class,
    ],
    version = 4
)
@TypeConverters(
    IntListTypeConverter::class,
    MovieTypeConverter::class,
)
abstract class MovieDatabase: RoomDatabase() {
    abstract val bookmarkMovieDao: BookmarkMovieDao
    abstract val ratingDao: RatingDao

    companion object {
        const val DB_NAME = "movie.db"
    }
}