package com.example.ratemovies.movie.presentation.movie_details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.ui.theme.RateMoviesTheme
import com.example.ratemovies.ui.theme.gold

@Composable
fun RatingColumn(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconTint: Color,
    voteAverage: String,
    voteCount: String,
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

        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = voteAverage,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "/10",
                modifier = Modifier.padding(bottom = 1.dp),
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        Text(
            text = voteCount,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = Dimens.MovieDetailsAlpha),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@PreviewLightDark
@Composable
fun RatingColumnPreview() {
    RateMoviesTheme {
        RatingColumn(
            modifier = Modifier,
            icon = Icons.Default.StarRate,
            iconTint = gold,
            voteAverage = "6,481",
            voteCount = "800",
        )
    }
}
