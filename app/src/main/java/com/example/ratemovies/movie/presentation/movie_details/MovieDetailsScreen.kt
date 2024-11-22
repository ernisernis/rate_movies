package com.example.ratemovies.movie.presentation.movie_details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.core.presentation.util.bottomInnerShadow
import com.example.ratemovies.core.presentation.util.errorPainter
import com.example.ratemovies.movie.domain.Cast
import com.example.ratemovies.movie.domain.Crew
import com.example.ratemovies.movie.domain.MovieDetails
import com.example.ratemovies.movie.domain.MovieGenre
import com.example.ratemovies.movie.presentation.models.defaultMovieUi
import com.example.ratemovies.movie.presentation.models.toMovieDetailsUi
import com.example.ratemovies.movie.presentation.movie_details.components.CastLazyHorizontalRow
import com.example.ratemovies.movie.presentation.movie_details.components.DetailRatings
import com.example.ratemovies.movie.presentation.movie_details.components.DirectorRow
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
                .verticalScroll(rememberScrollState())
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
                    modifier =
                        Modifier.padding(
                            start = Dimens.MovieDetailContainerPadding,
                            bottom = Dimens.MovieDetailContainerPadding / 2,
                        ),
                    releaseDate = state.movieDetailsUi?.releaseDate,
                    runtime = state.movieDetailsUi?.runtime?.formatted,
                )

                // Ratings
                DetailRatings(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(Dimens.MovieDetailComponentPadding),
                    voteAverage = state.movieDetailsUi?.voteAverage,
                    voteCount = state.movieDetailsUi?.voteCount?.formatted,
                )

                // Genre list
                GenreRow(
                    modifier = Modifier.horizontalScroll(rememberScrollState()).padding(Dimens.MovieDetailComponentPadding),
                    genres = state.movieDetailsUi?.genres,
                )
                // Overview (movie description)
                Text(
                    text = state.movieDetailsUi?.overview ?: "",
                    modifier = Modifier.padding(Dimens.MovieDetailComponentPadding),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = Dimens.MovieDetailsAlpha),
                )
                // Director, Writer
                DirectorRow(
                    modifier = Modifier.padding(Dimens.MovieDetailComponentPadding),
                    director = state.movieDetailsUi?.director,
                    writer = state.movieDetailsUi?.writer,
                )
                // Light Divider
                HorizontalDivider(
                    modifier = Modifier.padding(Dimens.MovieDetailComponentPadding),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                )
                // Cast title
                Text(
                    text = "Starring",
                    modifier = Modifier.padding(Dimens.MovieDetailComponentPadding),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                // Cast tiles
                CastLazyHorizontalRow(
                    list = state.movieDetailsUi?.cast,
                    modifier = Modifier.height(200.dp),
                )

                Spacer(modifier = Modifier.height(100.dp))
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
        voteCount = 800,
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
        cast =
            listOf(
                Cast(
                    id = 2524,
                    name = "Tom Hardy",
                    profilePath = "/d81K0RH8UX7tZj49tZaQhZ9ewH.jpg",
                    character = "Eddie Brock / Venom",
                ),
                Cast(
                    id = 2524,
                    name = "Tom Hardy",
                    profilePath = "/d81K0RH8UX7tZj49tZaQhZ9ewH.jpg",
                    character = "Eddie Brock / Venom",
                ),
            ),
        crew =
            listOf(
                Crew(
                    id = 1195200,
                    name = "David Michelinie",
                    job = "Characters",
                    profilePath = "/bSpGO1dKFfwb9mUc4KdUpBpRfYH.jpg",
                ),
                Crew(
                    id = 1195200,
                    name = "David Michelinie",
                    job = "Director",
                    profilePath = "/bSpGO1dKFfwb9mUc4KdUpBpRfYH.jpg",
                ),
            ),
    )
