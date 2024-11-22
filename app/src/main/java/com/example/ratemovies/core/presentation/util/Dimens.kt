package com.example.ratemovies.core.presentation.util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Dimens {
    val MovieListItemContainerPadding: Dp
        @Composable get() = 6.dp

    val MovieListItemIconSize: Dp
        @Composable get() = 14.dp

    val MovieScreenContainerPadding: Dp
        @Composable get() = 16.dp

    val MovieDetailContainerPadding: Dp
        @Composable get() = 16.dp

    val MovieDetailComponentPadding: PaddingValues
        @Composable get() = PaddingValues(horizontal = MovieDetailContainerPadding, vertical = MovieDetailContainerPadding / 2)

    val MovieDetailIconSize: Dp
        @Composable get() = 20.dp

    val MovieDetailsAlpha: Float
        get() = 0.8f
}