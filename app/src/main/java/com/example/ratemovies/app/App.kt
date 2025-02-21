package com.example.ratemovies.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.ratemovies.core.presentation.util.BottomNavigationVisibility
import com.example.ratemovies.movie.presentation.SelectedMovieViewModel
import com.example.ratemovies.movie.presentation.movie_bookmark.MovieBookmarkScreenRoot
import com.example.ratemovies.movie.presentation.movie_bookmark.MovieBookmarkViewModel
import com.example.ratemovies.movie.presentation.movie_detail.MovieDetailAction
import com.example.ratemovies.movie.presentation.movie_detail.MovieDetailScreenRoot
import com.example.ratemovies.movie.presentation.movie_list.MovieListScreenRoot
import com.example.ratemovies.movie.presentation.movie_rate.MovieRateAction
import com.example.ratemovies.movie.presentation.movie_rate.MovieRateScreenRoot


@Composable
fun App() {

    val navController = rememberNavController()
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val bottomBarState = rememberSaveable {
        mutableStateOf(true)
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        when (destination.id) {
            Route.MovieList.hashCode() -> {
                selectedIndex = 0
                bottomBarState.value = true
            }
            Route.MovieBookmark.hashCode() -> {
                selectedIndex = 1
                bottomBarState.value = true
            }
            else -> {
                bottomBarState.value = false
            }
        }
    }

    Scaffold(
        bottomBar = {
           BottomNavigationVisibility(
               visible = bottomBarState.value
           ) {
               NavigationBar(
                   modifier = Modifier
                       .navigationBarsPadding(),
                   containerColor = MaterialTheme.colorScheme.surface
               ) {
                   topLevelRoutes.forEachIndexed { index, topLevelRoute ->
                       NavigationBarItem(
                           icon = { Icon(topLevelRoute.icon, contentDescription = topLevelRoute.name) } ,
                           selected = index == selectedIndex,
                           onClick = {
                               selectedIndex = index
                               navController.navigate(topLevelRoute.route) {
                                   // Pop up to the start destination of the graph to
                                   // avoid building up a large stack of destinations
                                   // on the back stack as users select items
                                   popUpTo(navController.graph.findStartDestination().id) {
                                       saveState = true
                                   }
                                   // Avoid multiple copies of the same destination when
                                   // reselecting the same item
                                   launchSingleTop = true
                                   // Restore state when reselecting a previously selected item
                                   restoreState = true
                               }
                           },
                           colors = NavigationBarItemDefaults.colors(
                               selectedIconColor = Color.White,
                               unselectedIconColor = Color.DarkGray,
                               indicatorColor = Color.Transparent
                           )
                       )
                   }
               }
           }
        }
    ) { innerPadding ->
        RmNavHost(
            navController = navController,
            modifier = Modifier
                .padding(innerPadding),
        )

    }
}

@Composable
fun RmNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background),
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

            composable<Route.MovieBookmark> {
                val viewModel = hiltViewModel<MovieBookmarkViewModel>()
                val selectedMovieViewModel = it.sharedHiltViewModel<SelectedMovieViewModel>(navController)

                MovieBookmarkScreenRoot(
                    viewModel = viewModel,
                    onMovieIdClick = { id ->
                        selectedMovieViewModel.onSelectMovieId(id)
                        navController.navigate(Route.MovieDetail(id))
                    }
                )
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