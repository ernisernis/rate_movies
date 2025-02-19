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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.ratemovies.R
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.movie.domain.Movie
import com.example.ratemovies.movie.presentation.models.MovieUi
import com.example.ratemovies.movie.presentation.models.toMovieUi
import com.example.ratemovies.ui.theme.RateMoviesTheme

@Composable
fun MovieListItem(
    modifier: Modifier = Modifier,
    movieUi: MovieUi,
    onClick: () -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        // Image
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f),
            model = movieUi.imageUrl,
            contentDescription = stringResource(R.string.description_movie_item),
            error = painterResource(R.drawable.placeholder_gradient),
            placeholder = painterResource(R.drawable.placeholder_gradient),
            contentScale = ContentScale.Fit,
        )

        // Rating row (Icon + Vote text)
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.MovieListItemPaddingSmall),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(Dimens.MovieListItemPaddingNormal),
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                modifier = Modifier
                    .size(Dimens.MovieListItemIconSize),
                contentDescription = stringResource(R.string.description_movie_star),
                tint = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = movieUi.voteAverage,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelMedium,
            )
        }

        // Title
        Text(
            text = movieUi.title,
            modifier = Modifier
                .padding(horizontal = Dimens.MovieListItemPaddingNormal)
                .weight(1f),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
        )

        // Subtitle
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.MovieListItemPaddingSmall),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(Dimens.MovieListItemPaddingNormal),
        ) {
            // Date
            Text(
                text = movieUi.releaseDate,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelSmall,
            )

            Spacer(modifier = Modifier.weight(1f))

            // Like icon
            Icon(
                imageVector = Icons.Default.ThumbUp,
                modifier = Modifier
                    .size(Dimens.MovieListItemIconSize),
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = stringResource(R.string.description_movie_thumb_up),
            )

            // Like count
            Text(
                text = movieUi.popularity,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Preview
@Composable
fun MovieListItemPreview() {
    RateMoviesTheme {
        MovieListItem(
            modifier =
                Modifier
                    .padding(24.dp)
                    .height(325.dp)
                    .width(154.dp),
            movieUi = movie.toMovieUi(),
            onClick = {},
        )
    }
}

internal val movie = Movie(
    id = 0,
    title = "Terrifier 3",
    adult = false,
    backdropPath = "",
    genreIds = listOf(1,3,4),
    originalLanguage = "ENG",
    originalTitle = "Terrifier 3",
    overview = "Five years after surviving Art the Clown's Halloween massacre",
    popularity = 3456.34,
    posterPath = "/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
    releaseDate = "2024.12.12",
    video = false,
    voteAverage = 123.3,
    voteCount = 12345,
)