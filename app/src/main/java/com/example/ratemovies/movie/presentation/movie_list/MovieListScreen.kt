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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.example.ratemovies.movie.presentation.movie_list.components.MovieListItemsSection
import com.example.ratemovies.movie.presentation.movie_list.components.previewMovie
import com.example.ratemovies.ui.theme.RateMoviesTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun MovieListScreen(
    state: MovieListState,
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()),
    ) {
        MovieListItemsSection(
            title = "Now Playing",
            movies = state.moviesUi,
            onClick = {}
        )
        MovieListItemsSection(
            title = "Popular",
            movies = state.moviesUi,
            onClick = {}
        )
        MovieListItemsSection(
            title = "Upcoming",
            movies = state.moviesUi,
            onClick = {}
        )
    }
}


@PreviewLightDark()
@Composable
fun MovieListScreenPreview() {
    RateMoviesTheme {
        MovieListScreen(
            state = MovieListState(
                moviesUi = (1..20).map {
                    previewMovie.copy(id = it)
                }
            ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}

