package com.example.ratemovies.movie.data.mappers

import com.example.ratemovies.core.presentation.util.getImageUrl
import com.example.ratemovies.core.presentation.util.getReleaseYear
import com.example.ratemovies.movie.data.database.entities.RatingEntity
import com.example.ratemovies.movie.domain.model.Rating
import com.example.ratemovies.movie.presentation.models.RatingUi

fun RatingEntity.toRating(): Rating {
    return Rating(
        id = id,
        posterPath = posterPath,
        title = title,
        releaseDate = releaseDate,
        runtimeFormatted = runtimeFormatted,
        voteAverage = voteAverage,
        description = description,
        userRating = userRating,
        creationTime = creationTime,
    )
}

fun Rating.toRatingUi(): RatingUi {
    return RatingUi(
        id = id,
        imageUrl = posterPath.getImageUrl(),
        title = title,
        releaseYear = releaseDate.getReleaseYear(),
        runtimeFormatted = runtimeFormatted,
        voteAverage = voteAverage,
        description = description,
        userRating = userRating,
        extended = false,
        tooltip = false,
    )
}
fun Rating.toRatingEntity(): RatingEntity {
    return RatingEntity(
        id = id,
        posterPath = posterPath,
        title = title,
        releaseDate = releaseDate,
        runtimeFormatted = runtimeFormatted,
        voteAverage = voteAverage,
        description = description,
        userRating = userRating,
        creationTime = creationTime,
    )
}