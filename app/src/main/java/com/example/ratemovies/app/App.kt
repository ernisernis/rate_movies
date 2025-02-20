package com.example.ratemovies.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.ratemovies.movie.presentation.movie_detail.MovieDetailViewModel
import com.example.ratemovies.movie.presentation.movie_list.MovieListViewModel
import com.example.ratemovies.movie.presentation.movie_rate.MovieRateViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.ratemovies.movie.presentation.SelectedMovieViewModel
import com.example.ratemovies.movie.presentation.movie_detail.MovieDetailAction
import com.example.ratemovies.movie.presentation.movie_detail.MovieDetailScreenRoot
import com.example.ratemovies.movie.presentation.movie_list.MovieListScreenRoot
import com.example.ratemovies.movie.presentation.movie_rate.MovieRateAction
import com.example.ratemovies.movie.presentation.movie_rate.MovieRateScreenRoot


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
                    val selectedMovieViewModel = it.sharedHiltViewModel<SelectedMovieViewModel>(navController)

                    LaunchedEffect(true) {
                        selectedMovieViewModel.onSelectMovie(null)
                    }

                    MovieListScreenRoot(
                        viewModel = viewModel,
                        onMovieClick = { movie ->
                            selectedMovieViewModel.onSelectMovie(movie)
                            navController.navigate(Route.MovieDetail(movie.id))
                        }
                    )
                }

                composable<Route.MovieDetail> {
                    val viewModel = hiltViewModel<MovieDetailViewModel>()
                    val selectedMovieViewModel = it.sharedHiltViewModel<SelectedMovieViewModel>(navController)
                    val selectedMovie by selectedMovieViewModel.selectedMovie.collectAsStateWithLifecycle()

                    LaunchedEffect(selectedMovie) {
                        selectedMovie?.let { movie ->
                            viewModel.onAction(MovieDetailAction.OnSelectedMovieChange(movie))
                        }
                    }

                    MovieDetailScreenRoot(
                        viewModel = viewModel,
                        onRateClick = { movie ->
                            // It seems like we are re-selecting the same movie, but we are updating 'MovieDetail' params on Movie data class
                            selectedMovieViewModel.onSelectMovie(movie)
                            navController.navigate(Route.MovieRate)
                        },
                        onBackClick = {
                           navController.navigateUp()
                        }
                    )
                }
                composable<Route.MovieRate> {
                    val viewModel = hiltViewModel<MovieRateViewModel>()
                    val selectedMovieViewModel = it.sharedHiltViewModel<SelectedMovieViewModel>(navController)
                    val selectedMovie by selectedMovieViewModel.selectedMovie.collectAsStateWithLifecycle()

                    LaunchedEffect(selectedMovie) {
                        selectedMovie?.let { movie ->
                            viewModel.onAction(MovieRateAction.OnSelectedMovieChange(movie))
                        }
                    }

                    MovieRateScreenRoot(
                        viewModel = viewModel,
                        onBackClick = {
                            navController.navigateUp()
                        },
                        onRateSubmitSuccess = {
                            navController.popBackStack<Route.MovieList>(inclusive = false)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedHiltViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(
        viewModelStoreOwner = parentEntry
    )
}