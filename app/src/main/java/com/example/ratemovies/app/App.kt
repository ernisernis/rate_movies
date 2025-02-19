package com.example.ratemovies.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ratemovies.core.navigation.CustomNavType
import com.example.ratemovies.core.navigation.Destination
import com.example.ratemovies.core.navigation.NavigationAction
import com.example.ratemovies.core.navigation.Navigator
import com.example.ratemovies.core.presentation.util.ObserveAsEvents
import com.example.ratemovies.movie.domain.MovieNavArgs
import com.example.ratemovies.movie.presentation.movie_details.MovieDetailsScreen
import com.example.ratemovies.movie.presentation.movie_details.MovieDetailsViewModel
import com.example.ratemovies.movie.presentation.movie_list.MovieListScreen
import com.example.ratemovies.movie.presentation.movie_list.MovieListViewModel
import com.example.ratemovies.movie.presentation.movie_rate.MovieRateScreen
import com.example.ratemovies.movie.presentation.movie_rate.MovieRateViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import kotlin.reflect.typeOf


@Composable
fun App() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val navController = rememberNavController()
        val navigator = koinInject<Navigator>()

        ObserveAsEvents(flow = navigator.navigationActions) { action ->
            when (action) {
                is NavigationAction.Navigate ->
                    navController.navigate(
                        action.destination,
                    ) {
                        action.navOptions(this)
                    }
                NavigationAction.NavigateUp -> navController.navigateUp()
            }
        }
        NavHost(
            navController = navController,
            startDestination = navigator.startDestination,
        ) {
            navigation<Destination.MoviesGraph>(
                startDestination = Destination.MoviesScreen,
            ) {
                composable<Destination.MoviesScreen> {
                    val viewModel = koinViewModel<MovieListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    MovieListScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = state,
                        onAction = viewModel::onAction,
                    )
                }
                composable<Destination.DetailScreen>(
                    typeMap =
                    mapOf(
                        typeOf<MovieNavArgs>() to CustomNavType.MovieNavType,
                    ),
                ) {
                    val viewModel = koinViewModel<MovieDetailsViewModel>()
                    val movieNavArgs = it.toRoute<Destination.DetailScreen>().movieNavArgs
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    LaunchedEffect(movieNavArgs) {
                        viewModel.initData(movieNavArgs = it.toRoute<Destination.DetailScreen>().movieNavArgs)
                    }

                    MovieDetailsScreen(
                        modifier = Modifier,
                        state = state,
                        onAction = viewModel::onAction,
                    )
                }
                composable<Destination.RateScreen>(
                    typeMap =
                    mapOf(
                        typeOf<MovieNavArgs>() to CustomNavType.MovieNavType,
                    ),
                ) {
                    val viewModel = koinViewModel<MovieRateViewModel>()
                    val movieNavArgs = it.toRoute<Destination.RateScreen>().movieNavArgs
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    LaunchedEffect(movieNavArgs) {
                        viewModel.initData(movieNavArgs = it.toRoute<Destination.DetailScreen>().movieNavArgs)
                    }

                    MovieRateScreen(
                        modifier = Modifier.fillMaxSize(),
                        state = state,
                        onAction = viewModel::onAction,
                    )
                }
            }
        }
    }
}