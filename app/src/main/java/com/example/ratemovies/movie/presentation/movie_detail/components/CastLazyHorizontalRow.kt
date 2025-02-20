package com.example.ratemovies.movie.presentation.movie_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.ratemovies.R
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.movie.presentation.models.CastUi
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun CastLazyHorizontalRow(
    modifier: Modifier = Modifier,
    list: List<CastUi>?,
) {
    if (list != null) {
        LazyHorizontalGrid(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(Dimens.MovieDetailContainerPadding),
            contentPadding = Dimens.MovieDetailComponentPadding,
            rows = GridCells.Fixed(2),
        ) {
            items(list) { castUi ->
                CastItem(castUi)
            }
        }
    }
}

@Composable
fun CastItem(castUi: CastUi) {
    Row(
        modifier = Modifier.width(264.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = castUi.profilePath,
            contentDescription = null,
            error = painterResource(R.drawable.poster780w1170hpreview),
            modifier = Modifier.width(60.dp).aspectRatio(2f / 3f).clip(RoundedCornerShape(20)),
        )
        Column(
            modifier = Modifier.padding(horizontal = Dimens.MovieDetailContainerPadding),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = castUi.name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = castUi.character,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = Dimens.MovieDetailAlpha),
            )
        }
    }
}

@PreviewLightDark
@Composable
fun CastLazyRowPreview() {
    RateMoviesTheme {
        CastLazyHorizontalRow(
            modifier = Modifier.height(200.dp),
            list =
                listOf(
                    CastUi(
                        id = 1,
                        name = "Philipp Mercury",
                        profilePath = "https://image.tmdb.org/t/p/h632/thpdVW7O1975GcA3eNs1H8UIlmd.jpg",
                        character = "Deadpool",
                    ),
                    CastUi(
                        id = 2,
                        name = "Philipp Mercury",
                        profilePath = "https://image.tmdb.org/t/p/h632/thpdVW7O1975GcA3eNs1H8UIlmd.jpg",
                        character = "Deadpool",
                    ),
                    CastUi(
                        id = 3,
                        name = "Philipp Mercury",
                        profilePath = "https://image.tmdb.org/t/p/h632/thpdVW7O1975GcA3eNs1H8UIlmd.jpg",
                        character = "Deadpool",
                    ),
                    CastUi(
                        id = 4,
                        name = "Philipp Mercury",
                        profilePath = "https://image.tmdb.org/t/p/h632/thpdVW7O1975GcA3eNs1H8UIlmd.jpg",
                        character = "Deadpool",
                    ),
                    CastUi(
                        id = 5,
                        name = "Philipp Mercury",
                        profilePath = "https://image.tmdb.org/t/p/h632/thpdVW7O1975GcA3eNs1H8UIlmd.jpg",
                        character = "Deadpool",
                    ),
                ),
        )
    }
}