package com.example.ratemovies.movie.presentation.models

import com.example.ratemovies.core.domain.util.round
import com.example.ratemovies.movie.domain.Movie
import kotlinx.serialization.Serializable

@Serializable
data class MovieUi(
    val id: Int,
    val title: String,
    val adult: Boolean,
    val overview: String,
    val imageUrl: String,
    val releaseDate: String,
    val genres: List<String>,
    val voteAverage: String,
    val popularity: String,
    val banner: String,
)

fun Movie.toMovieUi(): MovieUi {
    return MovieUi(
        id = id,
        title = title,
        adult = adult,
        overview = overview,
        imageUrl = "https://image.tmdb.org/t/p/w780$posterPath",
        releaseDate = releaseDate,
        genres = emptyList(),
        voteAverage = voteAverage.round(1).toString(),
        popularity = popularity.toInt().toString(),
        banner = "https://image.tmdb.org/t/p/w1280$backdropPath",
    )
}

fun defaultMovieUi(): MovieUi {
    return MovieUi(
        id = 999999,
        title = "Blade Runner 2049",
        adult = false,
        overview =
            "Young Blade Runner K's discovery of a long-buried secret leads him to track down former Blade Runner " +
                "Rick Deckard, who's been missing for thirty years.",
        imageUrl = "",
        releaseDate = "2017-10-06",
        genres = listOf("Cyberpunk", "Sci-Fi Epic"),
        voteAverage = "9.3",
        popularity = "4968",
        banner = "https://image.tmdb.org/t/p/w1280/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg",
    )
}