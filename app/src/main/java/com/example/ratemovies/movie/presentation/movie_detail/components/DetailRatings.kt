package com.example.ratemovies.movie.presentation.movie_detail.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun DetailRatings(
    modifier: Modifier = Modifier,
    voteAverage: String?,
    voteCount: String?,
    onRatingClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        RatingColumn(
            modifier = Modifier,
            icon = Icons.Default.StarRate,
            iconTint = MaterialTheme.colorScheme.primary,
            voteAverage = voteAverage ?: "",
            voteCount = voteCount ?: "",
        )

        RatingAvailable(
            modifier =
                Modifier.clickable(true) {
                    onRatingClick()
                },
            icon = Icons.Default.StarOutline,
            iconTint = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@PreviewLightDark
@Composable
fun DetailRatingsPreview() {
    RateMoviesTheme {
        DetailRatings(
            modifier = Modifier.fillMaxWidth(),
            voteAverage = "7.4",
            voteCount = "157,364",
            onRatingClick = {},
        )
    }
}
