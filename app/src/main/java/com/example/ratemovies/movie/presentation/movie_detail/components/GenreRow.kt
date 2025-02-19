package com.example.ratemovies.movie.presentation.movie_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.movie.presentation.models.MovieGenreUi
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun GenreRow(
    modifier: Modifier = Modifier,
    genres: List<MovieGenreUi>?,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        genres?.forEach { genre ->
            Box(
                modifier =
                    Modifier.background(
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(50),
                    ).padding(vertical = 8.dp, horizontal = 14.dp),
            ) {
                Text(genre.name, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }
    }
}

@Composable
@PreviewLightDark
fun GenreRowPreview() {
    RateMoviesTheme {
        GenreRow(
            modifier = Modifier.horizontalScroll(rememberScrollState()).padding(Dimens.MovieDetailContainerPadding),
            genres =
                listOf(
                    MovieGenreUi(
                        id = 878,
                        name = "Science Fiction",
                    ),
                    MovieGenreUi(
                        id = 28,
                        name = "Action",
                    ),
                    MovieGenreUi(
                        id = 12,
                        name = "Adventure",
                    ),
                    MovieGenreUi(
                        id = 878,
                        name = "Science Fiction",
                    ),
                    MovieGenreUi(
                        id = 28,
                        name = "Action",
                    ),
                    MovieGenreUi(
                        id = 12,
                        name = "Adventure",
                    ),
                ),
        )
    }
}
