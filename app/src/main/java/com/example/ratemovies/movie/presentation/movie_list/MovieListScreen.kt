package com.example.ratemovies.movie.presentation.movie_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ratemovies.movie.domain.Movie
import com.example.ratemovies.movie.presentation.movie_list.components.MovieListItemsSection
import com.example.ratemovies.movie.presentation.movie_list.components.MovieListLoading
import com.example.ratemovies.movie.presentation.movie_list.components.movie
import com.example.ratemovies.ui.theme.RateMoviesTheme


@Composable
fun MovieListScreenRoot(
    viewModel: MovieListViewModel = hiltViewModel(),
    onMovieClick: (Movie) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.isLoading) {
        MovieListLoading(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
        )
    } else {
        MovieListScreen(
            state = state,
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            onAction = { action ->
                when (action) {
                    is MovieListAction.OnMovieClick -> onMovieClick(action.movie)
                    else -> Unit
                }
                viewModel.onAction(action)
            }
        )
    }
}

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
            modifier = modifier,
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
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Preview
@Composable
fun MovieListScreenPreview() {
    RateMoviesTheme {
        MovieListScreen(
            state =
                MovieListState(
                    nowPlayingMoviesUi =
                        (1..20).map {
                            movie.copy(id = it)
                        },
                ),
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            onAction = {},
        )
    }
}