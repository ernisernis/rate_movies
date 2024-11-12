package com.example.ratemovies

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.example.ratemovies.movie.presentation.models.MovieUi
import com.example.ratemovies.movie.presentation.movie_detail.MovieDetailScreen
import com.example.ratemovies.movie.presentation.movie_detail.MovieDetailViewModel
import com.example.ratemovies.movie.presentation.movie_list.MovieListScreen
import com.example.ratemovies.movie.presentation.movie_list.MovieListViewModel
import com.example.ratemovies.ui.theme.RateMoviesTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RateMoviesTheme {
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
                                        typeOf<MovieUi>() to CustomNavType.MovieUiType,
                                    ),
                            ) {
                                val viewModel = koinViewModel<MovieDetailViewModel>()
                                val state by viewModel.state.collectAsStateWithLifecycle()

                                val arguments = it.toRoute<Destination.DetailScreen>()
                                Log.d("ERNIS33", "onCreate: $arguments")

                                MovieDetailScreen(
                                    modifier = Modifier,
                                    state = state,
                                    onAction = viewModel::onAction,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}