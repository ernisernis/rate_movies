package com.example.ratemovies.movie.presentation.movie_profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.ratemovies.R
import com.example.ratemovies.core.presentation.util.Dimens
import com.example.ratemovies.core.presentation.util.RmIcons
import com.example.ratemovies.movie.presentation.components.PosterImage
import com.example.ratemovies.movie.presentation.models.RatingUi

@Composable
fun MovieProfileListItem(
    modifier: Modifier = Modifier,
    ratingUi: RatingUi,
    description: @Composable () -> Unit,
    onClick: () -> Unit,
    onTooltipClick: () -> Unit,
    onDismissTooltip: () -> Unit,
    onReviewDelete: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(Dimens.ProfileItemPaddingNormal),
        horizontalArrangement = Arrangement.spacedBy(Dimens.ProfileItemPaddingNormal),
    ) {

        PosterImage(
            modifier = Modifier
                .width(Dimens.ProfileRatingItemImageWidth),
            url = ratingUi.imageUrl,
        )

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(Dimens.ProfileItemPaddingSmall),
                ) {
                    // Title
                    Text(
                        text = ratingUi.title,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 18.sp,
                    )

                    // Year + runtime
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(Dimens.ProfileItemPaddingNormal)
                    ) {
                        Text(
                            text = ratingUi.releaseYear,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = Dimens.ProfileAlpha),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                        ratingUi.runtimeFormatted?.let {
                            Text(
                                text = it,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = Dimens.ProfileAlpha),
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }

                    // Movie rating + User rating
                    Row(
                        modifier = Modifier,
                        horizontalArrangement = Arrangement.spacedBy(Dimens.ProfileItemPaddingSmall),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = RmIcons.StarRate,
                            contentDescription = stringResource(R.string.description_movie_star),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(Dimens.ProfileIconSize)
                        )
                        Text(
                            text = ratingUi.voteAverage,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyMedium,
                        )

                        Spacer(modifier = Modifier.width(Dimens.ProfileItemPaddingSmall))

                        Icon(
                            imageVector = RmIcons.StarRate,
                            contentDescription = stringResource(R.string.description_movie_star),
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier
                                .size(Dimens.ProfileIconSize)
                        )
                        Text(
                            text = ratingUi.userRating.toString(),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyMedium,
                        )

                    }
                }

                Box {
                    DropdownMenu(
                        expanded = ratingUi.tooltip,
                        onDismissRequest = {
                            onDismissTooltip()
                        },
                        containerColor = MaterialTheme.colorScheme.secondary
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(R.string.profile_review_delete),
                                    color = MaterialTheme.colorScheme.onSecondary,
                                )
                            },
                            onClick = {
                                onReviewDelete()
                            }
                        )
                    }
                    IconButton(
                        onClick = {
                            onTooltipClick()
                        }
                    ) {
                        Icon(
                            imageVector = RmIcons.MoreVert,
                            contentDescription = stringResource(R.string.more_options),
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }

            }

            description()
        }

    }
}