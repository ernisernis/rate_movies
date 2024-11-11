package com.example.ratemovies.movie.presentation.movie_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.ratemovies.R
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.movie.presentation.models.MovieUi
import com.example.ratemovies.ui.theme.RateMoviesTheme
import com.example.ratemovies.ui.theme.gold

@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    movieUi: MovieUi,
    onClick: () -> Unit,
) {
    ElevatedCard(
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 12.dp,
            ),
        colors =
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
        modifier = modifier.clickable(onClick = onClick),
    ) {
        AsyncImage(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f),
            model = movieUi.imageUrl,
            contentDescription = null,
            placeholder = painterResource(R.drawable.poster780w1170hpreview),
            error = painterResource(R.drawable.poster780w1170hpreview),
            contentScale = ContentScale.Fit,
        )

        // Rating
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.MovieListItemContainerPadding / 2),
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .padding(Dimens.MovieListItemContainerPadding),
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                modifier =
                    Modifier
                        .size(Dimens.MovieListItemIconSize),
                tint = gold,
                contentDescription = null,
            )
            Text(
                text = movieUi.voteAverage,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.labelMedium,
            )
        }

        // Title
        Text(
            text = movieUi.title,
            modifier =
                Modifier
                    .padding(horizontal = Dimens.MovieListItemContainerPadding)
                    .weight(1f),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.labelMedium,
        )

        // Subtitle
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.MovieListItemContainerPadding / 2),
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .padding(Dimens.MovieListItemContainerPadding),
        ) {
            // Date
            Text(
                text = movieUi.releaseDate,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelSmall,
            )

            Spacer(modifier = Modifier.weight(1f))

            // Like icon
            Icon(
                imageVector = Icons.Default.ThumbUp,
                modifier =
                    Modifier
                        .size(Dimens.MovieListItemIconSize),
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                contentDescription = null,
            )

            // Like count
            Text(
                text = movieUi.popularity,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@PreviewLightDark()
@Preview(
    showSystemUi = true,
    device = Devices.NEXUS_5X,
)
@Composable
fun MovieListItemPreview() {
    RateMoviesTheme {
        MovieListItem(
            modifier =
                Modifier
                    .padding(24.dp)
                    .height(325.dp)
                    .width(154.dp),
            movieUi = previewMovie,
            onClick = {},
        )
    }
}

internal val previewMovie =
    MovieUi(
        id = 1,
        title = "Terrifier 3",
        adult = false,
        overview = "Five years after surviving Art the Clown's Halloween massacre",
        imageUrl = "https://image.tmdb.org/t/p/w500/63xYQj1BwRFielxsBDXvHIJyXVm.jpg",
        releaseDate = "2024-10-09",
        genres = listOf("Horror", "Comedy", "Sci-fi"),
        voteAverage = "7.3",
        popularity = "3545",
        banner = "https://image.tmdb.org/t/p/w1280/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg",
    )