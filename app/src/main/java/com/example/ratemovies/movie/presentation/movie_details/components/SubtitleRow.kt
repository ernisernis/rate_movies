package com.example.ratemovies.movie.presentation.movie_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.ratemovies.core.domain.util.round
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.movie.presentation.models.toDisplayableRuntime
import com.example.ratemovies.movie.presentation.movie_details.defaultMovieDetails
import com.example.ratemovies.ui.theme.RateMoviesTheme
import com.example.ratemovies.ui.theme.gold

@Composable
fun SubtitleRow(
    modifier: Modifier = Modifier,
    releaseDate: String?,
    runtime: String?,
    voteAverage: String?,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = releaseDate ?: "", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
        Text(text = runtime ?: "", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                modifier =
                    Modifier
                        .size(Dimens.MovieListItemIconSize),
                tint = gold,
                contentDescription = null,
            )
            Text(text = voteAverage ?: "", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
        }
    }
}

@PreviewLightDark
@Composable
fun SubtitleRowPreview() {
    RateMoviesTheme {
        SubtitleRow(
            releaseDate = defaultMovieDetails.releaseDate,
            runtime = defaultMovieDetails.runtime.toDisplayableRuntime().formatted,
            voteAverage = defaultMovieDetails.voteAverage.round(1).toString(),
        )
    }
}