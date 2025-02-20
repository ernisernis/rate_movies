package com.example.ratemovies.movie.presentation.movie_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.ratemovies.R
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.core.presentation.util.bottomInnerShadow
import com.example.ratemovies.core.presentation.util.errorPainter
import com.example.ratemovies.movie.domain.Cast
import com.example.ratemovies.movie.domain.Crew
import com.example.ratemovies.movie.domain.Movie
import com.example.ratemovies.movie.domain.MovieDetail
import com.example.ratemovies.movie.domain.MovieGenre
import com.example.ratemovies.movie.presentation.movie_detail.components.CastLazyHorizontalRow
import com.example.ratemovies.movie.presentation.movie_detail.components.DetailRatings
import com.example.ratemovies.movie.presentation.movie_detail.components.DirectorRow
import com.example.ratemovies.movie.presentation.movie_detail.components.GenreRow
import com.example.ratemovies.movie.presentation.movie_detail.components.SubtitleRow
import com.example.ratemovies.movie.presentation.movie_detail.components.TitleRow

@Composable
fun MovieDetailScreenRoot(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    onRateClick: (Movie) -> Unit,
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MovieDetailScreen(
        state = state,
        modifier = Modifier
            .fillMaxSize(),
        onAction = { action ->
           when (action)  {
               is MovieDetailAction.OnRateClick -> {
                   // TODO:
               }
               is MovieDetailAction.OnBackClick -> onBackClick()
               else -> Unit
           }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun MovieDetailScreen(
    state: MovieDetailState,
    modifier: Modifier = Modifier,
    onAction: (MovieDetailAction) -> Unit
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            // Banner
            AsyncImage(
                model = state.movieUi?.banner,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
                    .bottomInnerShadow(),
                contentDescription = stringResource(R.string.description_banner),
                error = errorPainter,
                placeholder = errorPainter,
                fallback = errorPainter,
            )
        }

        // Title and Image
        TitleRow(
            modifier = Modifier
                .padding(horizontal = Dimens.MovieDetailContainerPadding),
            title = state.movieUi?.title,
            imageUrl = state.movieUi?.imageUrl,
        )

        AnimatedVisibility(
            visible = state.movieUi?.movieDetailUi != null
        ) {
            Column {
                // Subtitle
                SubtitleRow(
                    modifier =
                        Modifier.padding(
                            start = Dimens.MovieDetailItemPaddingBig,
                            bottom = Dimens.MovieDetailItemPaddingNormal,
                        ),
                    releaseDate = state.movieUi?.movieDetailUi?.releaseDate,
                    runtime = state.movieUi?.movieDetailUi?.runtime?.formatted,
                )

                // Ratings
                DetailRatings(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.MovieDetailComponentPadding),
                    voteAverage = state.movieUi?.movieDetailUi?.voteAverage,
                    voteCount = state.movieUi?.movieDetailUi?.voteCount?.formatted,
                    onRatingClick = { onAction(MovieDetailAction.OnRateClick) },
                )

                // Genre list
                GenreRow(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(Dimens.MovieDetailComponentPadding),
                    genres = state.movieUi?.movieDetailUi?.genres,
                )
                // Overview (movie description)
                Text(
                    text = state.movieUi?.movieDetailUi?.overview ?: "",
                    modifier = Modifier
                        .padding(Dimens.MovieDetailComponentPadding),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = Dimens.MovieDetailAlpha),
                )
                // Director, Writer
                DirectorRow(
                    modifier = Modifier
                        .padding(Dimens.MovieDetailComponentPadding),
                    director = state.movieUi?.movieDetailUi?.director,
                    writer = state.movieUi?.movieDetailUi?.writer,
                )
                // Light Divider
                HorizontalDivider(
                    modifier = Modifier
                        .padding(Dimens.MovieDetailComponentPadding),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                )
                // Cast title
                Text(
                    text = stringResource(R.string.movie_detail_starring),
                    modifier = Modifier
                        .padding(Dimens.MovieDetailComponentPadding),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                state.movieUi?.movieDetailUi?.cast?.let { cast ->
                   // Light Divider
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(Dimens.MovieDetailComponentPadding),
                        thickness = 0.5.dp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                    )
                    // Cast title
                    Text(
                        text = stringResource(R.string.movie_detail_starring),
                        modifier = Modifier
                            .padding(Dimens.MovieDetailComponentPadding),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    // Cast tiles
                    CastLazyHorizontalRow(
                        modifier = Modifier
                            .height(200.dp),
                        list = cast,
                    )
                }

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}


// TODO:
//@Composable
//@PreviewLightDark
//private fun MovieDetailsScreenPreview() {
//    RateMoviesTheme {
//        MovieDetailsScreen(
//            state =
//                MovieDetailState(
//                    bannerUrl = defaultMovieUi().banner,
//                    title = defaultMovieUi().title,
//                    imageUrl = defaultMovieUi().imageUrl,
//                    movieDetailsUi = defaultMovieDetail.toMovieDetailsUi(),
//                ),
//            modifier = Modifier,
//            onAction = {},
//        )
//    }
//}

internal val defaultMovieDetail =
    MovieDetail(
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
