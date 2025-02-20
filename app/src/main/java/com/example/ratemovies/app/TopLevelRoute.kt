package com.example.ratemovies.app

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ratemovies.core.presentation.util.RmIcons


data class TopLevelRoute<T: Any> (val name: String, val route: T, val icon: ImageVector)

val topLevelRoutes = listOf(
    TopLevelRoute("Home", Route.MovieGraph, RmIcons.Home),
    TopLevelRoute("Bookmark", Route.MovieBookmark, RmIcons.Bookmarks),
)