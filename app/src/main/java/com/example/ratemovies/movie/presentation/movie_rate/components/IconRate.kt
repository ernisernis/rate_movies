package com.example.ratemovies.movie.presentation.movie_rate.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun IconRate(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    Icon(
        imageVector = icon,
        contentDescription = "Rating icon",
        tint = MaterialTheme.colorScheme.onBackground,
        modifier =
            modifier.clickable(enabled = true) {
                onClick()
            },
    )
}

@Preview
@Composable
fun IconRatePreview() {
    RateMoviesTheme {
        IconRate(
            modifier = Modifier.size(32.dp),
            icon = Icons.Default.Star,
            onClick = {},
        )
    }
}