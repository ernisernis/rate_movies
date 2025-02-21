package com.example.ratemovies.movie.presentation.movie_bookmark.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ratemovies.R
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.core.presentation.util.RmIcons
import com.example.ratemovies.movie.domain.BookmarkMovie
import com.example.ratemovies.movie.presentation.components.DefaultIconContainer
import com.example.ratemovies.movie.presentation.components.PosterImage
import com.example.ratemovies.movie.presentation.models.BookmarkMovieUi
import com.example.ratemovies.movie.presentation.models.toBookmarkMovieUi
import com.example.ratemovies.ui.theme.RateMoviesTheme


@Composable
fun MovieBookmarkListItem(
    modifier: Modifier = Modifier,
    bookmarkMovieUi: BookmarkMovieUi,
    onClick: () -> Unit,
    onBookmarkClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(Dimens.MovieBookmarkItemPaddingNormal),
        horizontalArrangement = Arrangement.spacedBy(
            Dimens.MovieBookmarkItemPaddingNormal
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        PosterImage(
            modifier = Modifier
                .width(Dimens.MovieBookmarkImageWidth),
            url = bookmarkMovieUi.imageUrl,
        )

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(
                Dimens.MovieBookmarkItemPaddingSmall,
                Alignment.CenterVertically
            ),
        ) {
            // Title
            Text(
                text = bookmarkMovieUi.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp,
            )

            // Year + runtime
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    Dimens.MovieBookmarkItemPaddingNormal
                )
            ) {
                Text(
                    text = bookmarkMovieUi.releaseYear,
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = Dimens.MovieBookmarkAlpha
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                )
                bookmarkMovieUi.runtimeFormatted?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = Dimens.MovieBookmarkAlpha
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

            // Star icon + rating
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(
                    Dimens.MovieBookmarkItemPaddingSmall
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = RmIcons.StarRate,
                    contentDescription = stringResource(R.string.description_movie_star),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(24.dp)
                )
                Text(
                    text = bookmarkMovieUi.voteAverage,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        DefaultIconContainer(
            icon = RmIcons.BookmarkSelected,
            modifier = Modifier
                .align(Alignment.Top),
            onClick = {
                onBookmarkClick()
            }
        )
    }
}

@Preview
@Composable
fun MovieBookmarkListItemPreview(
    modifier: Modifier = Modifier,
) {
    RateMoviesTheme {
        MovieBookmarkListItem(
            modifier = Modifier
                .padding(15.dp),
            bookmarkMovieUi = defaultBookmarkMovie.toBookmarkMovieUi(),
            onClick = {},
            onBookmarkClick = {}
        )
    }
}

internal val defaultBookmarkMovie =
    BookmarkMovie (
        id = 0,
        posterPath = "/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
        title = "Bookmark Movie Title",
        releaseDate = "2011-07-24",
        runtimeFormatted = "2h 14min",
        voteAverage = "7.3",
        creationTime = 20000L,
    )
