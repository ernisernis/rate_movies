package com.example.ratemovies.core.presentation.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Dimens {
    val MovieScreenContainerPadding: Dp
        @Composable get() = 16.dp

    val MovieDetailsAlpha: Float
        get() = 0.8f
    /*
        movie_list
     */

    val MovieListItemContainerPadding: Dp
        @Composable get() = 6.dp

    val MovieListItemIconSize: Dp
        @Composable get() = 14.dp

    /*
        movie_details
     */

    val MovieDetailContainerPadding: Dp
        @Composable get() = 16.dp

    val MovieDetailComponentPadding: PaddingValues
        @Composable get() = PaddingValues(horizontal = MovieDetailContainerPadding, vertical = MovieDetailContainerPadding / 2)

    /*
        movie_rate
     */

    val MovieRateContainerPadding: Dp
        @Composable get() = 16.dp

    val MovieRateComponentPadding: PaddingValues
        @Composable get() = PaddingValues(horizontal = MovieRateContainerPadding, vertical = MovieRateContainerPadding / 2)
}