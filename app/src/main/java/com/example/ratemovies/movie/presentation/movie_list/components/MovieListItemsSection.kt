package com.example.ratemovies.movie.presentation.movie_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.movie.domain.Movie
import com.example.ratemovies.movie.presentation.models.toMovieUi
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun MovieListItemsSection(
    modifier: Modifier = Modifier,
    title: String,
    movies: List<Movie>,
    onClick: (Movie) -> Unit,
) {
    Column {
        // Title
        Text(
            text = title,
            modifier = Modifier
                .padding(horizontal = Dimens.MovieListContainerPadding),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium,
        )

        // Movie items
        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(Dimens.MovieListItemPaddingNormal),
            contentPadding = PaddingValues(Dimens.MovieListContainerPadding),
        ) {
            items(movies) { movie ->
                MovieListItem(
                    movieUi = movie.toMovieUi(),
                    modifier =
                        Modifier
                            .height(Dimens.MovieListItemHeight)
                            .width(Dimens.MovieListItemWidth),
                    onClick = { onClick(movie) },
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun MovieListItemsSectionPreview() {
    RateMoviesTheme {
        MovieListItemsSection(
            modifier = Modifier,
            title = "Now Playing",
            movies =
                (1..20).map {
                    movie.copy(id = it)
                },
            onClick = {},
        )
    }
}