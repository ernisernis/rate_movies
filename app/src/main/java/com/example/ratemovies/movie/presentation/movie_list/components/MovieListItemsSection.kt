package com.example.ratemovies.movie.presentation.movie_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.movie.presentation.models.MovieUi
import com.example.ratemovies.ui.theme.RateMoviesTheme


@Composable
fun ColumnScope.MovieListItemsSection(
    modifier: Modifier = Modifier,
    title: String,
    movies: List<MovieUi>,
    onClick: (MovieUi) -> Unit
) {
    // Title
    Text(
        text = title,
        modifier = Modifier.padding(horizontal = Dimens.MovieScreenContainerPadding),
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.headlineMedium
    )

    // Movie items
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(Dimens.MovieScreenContainerPadding)
    ) {
        items(movies) { movieUi ->
            MovieListItem(
                movieUi = movieUi,
                modifier = Modifier.height(325.dp).width(154.dp),
                onClick = { onClick(movieUi) }
            )
        }
    }
}



@PreviewLightDark
@Composable
fun MovieListItemsSectionPreview() {
    RateMoviesTheme {
        Column {
            MovieListItemsSection(
                modifier = Modifier,
                title = "Now Playing",
                movies = (1..20).map {
                    previewMovie.copy(id = it)
                },
                onClick = {}
            )
        }
    }
}