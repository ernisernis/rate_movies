package com.example.ratemovies.movie.presentation.movie_details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil3.compose.AsyncImage
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.core.presentation.util.bottomInnerShadow
import com.example.ratemovies.core.presentation.util.errorPainter
import com.example.ratemovies.movie.domain.MovieDetails
import com.example.ratemovies.movie.domain.MovieGenre
import com.example.ratemovies.movie.presentation.models.defaultMovieUi
import com.example.ratemovies.movie.presentation.models.toMovieDetailsUi
import com.example.ratemovies.movie.presentation.movie_details.components.GenreRow
import com.example.ratemovies.movie.presentation.movie_details.components.SubtitleRow
import com.example.ratemovies.movie.presentation.movie_details.components.TitleRow
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun MovieDetailsScreen(
    state: MovieDetailsState,
    modifier: Modifier = Modifier,
    onAction: (MovieDetailsAction) -> Unit,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
    ) {
        // Banner
        AsyncImage(
            model = state.bannerUrl,
            contentDescription = "Banner Image",
            error = errorPainter,
            placeholder = errorPainter,
            fallback = errorPainter,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .bottomInnerShadow(),
        )
        // Title and Image
        TitleRow(
            modifier =
                Modifier
                    .padding(horizontal = Dimens.MovieDetailContainerPadding),
            title = state.title,
            imageUrl = state.imageUrl,
        )

        AnimatedVisibility(visible = state.movieDetailsUi != null) {
            Column {
                // Subtitle
                SubtitleRow(
                    modifier = Modifier.padding(start = Dimens.MovieDetailContainerPadding),
                    releaseDate = state.movieDetailsUi?.releaseDate,
                    runtime = state.movieDetailsUi?.runtime?.formatted,
                    voteAverage = state.movieDetailsUi?.voteAverage,
                )
                // Genre list
                GenreRow(
                    modifier = Modifier.horizontalScroll(rememberScrollState()).padding(Dimens.MovieDetailContainerPadding),
                    genres = state.movieDetailsUi?.genres,
                )
                // Overview (movie description)
                Text(
                    text = state.movieDetailsUi?.overview ?: "",
                    modifier = Modifier.padding(horizontal = Dimens.MovieDetailContainerPadding),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun MovieDetailsScreenPreview() {
    RateMoviesTheme {
        MovieDetailsScreen(
            state =
                MovieDetailsState(
                    bannerUrl = defaultMovieUi().banner,
                    title = defaultMovieUi().title,
                    imageUrl = defaultMovieUi().imageUrl,
                    movieDetailsUi = defaultMovieDetails.toMovieDetailsUi(),
                ),
            modifier = Modifier,
            onAction = {},
        )
    }
}

internal val defaultMovieDetails =
    MovieDetails(
        id = 912649,
        releaseDate = "2024-10-22",
        runtime = 109,
        voteAverage = 6.387,
        genres =
            listOf(
                MovieGenre(
                    id = 878,
                    name = "Science Fiction",
                ),
                MovieGenre(
                    id = 28,
                    name = "Action",
                ),
                MovieGenre(
                    id = 12,
                    name = "Adventure",
                ),
            ),
        overview =
            "Eddie and Venom are on the run. Hunted by both of their worlds and with the net closing in, " +
                "the duo are forced into a devastating decision that will bring the curtains down on Venom and Eddie's last dance",
    )
