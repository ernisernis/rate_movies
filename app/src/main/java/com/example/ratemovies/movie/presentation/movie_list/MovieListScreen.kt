package com.example.ratemovies.movie.presentation.movie_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.ratemovies.movie.presentation.movie_list.components.MovieListItemsSection
import com.example.ratemovies.movie.presentation.movie_list.components.MovieListLoading
import com.example.ratemovies.movie.presentation.movie_list.components.previewMovie
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun MovieListScreen(
    state: MovieListState,
    modifier: Modifier = Modifier,
    onAction: (MovieListAction) -> Unit,
) {
    if (state.isLoading) {
        MovieListLoading()
    } else {
        Column(
            modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        ) {
            MovieListItemsSection(
                title = "Now Playing",
                movies = state.nowPlayingMoviesUi,
                onClick = { onAction(MovieListAction.OnMovieClick(it)) },
            )
            MovieListItemsSection(
                title = "Popular",
                movies = state.popularMoviesUi,
                onClick = { onAction(MovieListAction.OnMovieClick(it)) },
            )
            MovieListItemsSection(
                title = "Top Rated",
                movies = state.topRatedMoviesUi,
                onClick = { onAction(MovieListAction.OnMovieClick(it)) },
            )
            MovieListItemsSection(
                title = "Upcoming",
                movies = state.upcomingMoviesUi,
                onClick = { onAction(MovieListAction.OnMovieClick(it)) },
            )
        }
    }
}

@PreviewLightDark()
@Composable
fun MovieListScreenPreview() {
    RateMoviesTheme {
        MovieListScreen(
            state =
                MovieListState(
                    nowPlayingMoviesUi =
                        (1..20).map {
                            previewMovie.copy(id = it)
                        },
                ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            onAction = {},
        )
    }
}