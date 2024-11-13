package com.example.ratemovies.movie.presentation.movie_details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil3.compose.AsyncImage
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.core.presentation.util.bottomInnerShadow
import com.example.ratemovies.core.presentation.util.errorPainter
import com.example.ratemovies.movie.domain.MovieDetails
import com.example.ratemovies.movie.presentation.models.defaultMovieUi
import com.example.ratemovies.movie.presentation.models.toMovieDetailsUi
import com.example.ratemovies.movie.presentation.movie_details.components.SubtitleRow
import com.example.ratemovies.movie.presentation.movie_details.components.TitleRow
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun MovieDetailScreen(
    state: MovieDetailsState,
    modifier: Modifier = Modifier,
    onAction: (MovieDetailsAction) -> Unit,
) {
    LazyColumn(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
    ) {
        // Banner
        item {
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
        }
        // Title and Image
        item {
            TitleRow(
                modifier =
                    Modifier
                        .padding(horizontal = Dimens.MovieDetailContainerPadding),
                title = state.title,
                imageUrl = state.imageUrl,
            )
        }
        // Subtitle
        item {
            AnimatedVisibility(visible = state.movieDetailsUi != null) {
                SubtitleRow(
                    modifier = Modifier.padding(start = Dimens.MovieDetailContainerPadding),
                    releaseDate = state.movieDetailsUi?.releaseDate,
                    runtime = state.movieDetailsUi?.runtime?.formatted,
                    voteAverage = state.movieDetailsUi?.voteAverage,
                )
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun MovieDetailScreenPreview() {
    RateMoviesTheme {
        MovieDetailScreen(
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
    )
