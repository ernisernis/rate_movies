package com.example.ratemovies.movie.presentation.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import coil3.compose.AsyncImage
import com.example.ratemovies.movie.presentation.models.defaultMovieUi
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun MovieDetailScreen(
    state: MovieDetailState,
    modifier: Modifier = Modifier,
    onAction: (MovieDetailAction) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),
    ) {
        // Banner
        item {
            AsyncImage(
                model = state.movieUi.banner,
                contentDescription = null,
            )
        }
    }
}

@Composable
@PreviewLightDark
private fun MovieDetailScreenPreview() {
    RateMoviesTheme {
        MovieDetailScreen(
            state =
                MovieDetailState(
                    movieUi = defaultMovieUi(),
                ),
            modifier = Modifier,
            onAction = {},
        )
    }
}
