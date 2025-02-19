package com.example.ratemovies.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.ratemovies.movie.presentation.movie_details.MovieDetailsScreen
import com.example.ratemovies.movie.presentation.movie_details.MovieDetailsViewModel
import com.example.ratemovies.movie.presentation.movie_list.MovieListViewModel
import com.example.ratemovies.movie.presentation.movie_rate.MovieRateScreen
import com.example.ratemovies.movie.presentation.movie_rate.MovieRateViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ratemovies.movie.presentation.movie_list.MovieListScreenRoot


@Composable
fun App() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.MovieGraph
        ) {
            navigation<Route.MovieGraph>(
                startDestination = Route.MovieList,
            ) {
                composable<Route.MovieList> {
                    val viewModel = hiltViewModel<MovieListViewModel>()

                    MovieListScreenRoot(
                        viewModel = viewModel,
                        onMovieClick = { movie ->
                            // TODO:
                        }
                    )
                }
                composable<Route.MovieDetail> {
                    val viewModel = hiltViewModel<MovieDetailsViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    MovieDetailsScreen(
                        modifier = Modifier,
                        state = state,
                        onAction = viewModel::onAction,
                    )
                }
                composable<Route.MovieRate> {
                    val viewModel = hiltViewModel<MovieRateViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()

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