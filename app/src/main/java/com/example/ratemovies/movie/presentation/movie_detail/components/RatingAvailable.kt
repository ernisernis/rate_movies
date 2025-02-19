package com.example.ratemovies.movie.presentation.movie_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun RatingAvailable(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconTint: Color,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Rating icon",
            tint = iconTint,
            modifier = Modifier.size(32.dp),
        )

        Text(
            text = "Rate this",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@PreviewLightDark
@Composable
fun RatingAvailablePreview() {
    RateMoviesTheme {
        RatingAvailable(
            modifier = Modifier,
            icon = Icons.Filled.StarOutline,
            iconTint = MaterialTheme.colorScheme.onBackground,
        )
    }
}