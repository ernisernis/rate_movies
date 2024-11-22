package com.example.ratemovies.movie.presentation.movie_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.ratemovies.movie.presentation.models.toDisplayableRuntime
import com.example.ratemovies.movie.presentation.movie_details.defaultMovieDetails
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun SubtitleRow(
    modifier: Modifier = Modifier,
    releaseDate: String?,
    runtime: String?,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = releaseDate ?: "", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground)
        Text(text = runtime ?: "", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onBackground)
    }
}

@PreviewLightDark
@Composable
fun SubtitleRowPreview() {
    RateMoviesTheme {
        SubtitleRow(
            releaseDate = defaultMovieDetails.releaseDate,
            runtime = defaultMovieDetails.runtime.toDisplayableRuntime().formatted,
        )
    }
}