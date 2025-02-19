package com.example.ratemovies.core.presentation.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Dimens {

    // movie_list
    val MovieListItemPaddingBig: Dp
        @Composable get() = 16.dp

    val MovieListItemPaddingNormal: Dp
        @Composable get() = 8.dp

    val MovieListItemPaddingSmall: Dp
        @Composable get() = 4.dp

    val MovieListItemHeight: Dp
        @Composable get() = 325.dp

    val MovieListItemWidth: Dp
        @Composable get() = 154.dp

    val MovieListItemIconSize: Dp
        @Composable get() = 14.dp

    val MovieListContainerPadding: Dp
        @Composable get() = 16.dp


    /*
        movie_details
     */
    val MovieDetailsAlpha: Float
        get() = 0.8f

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