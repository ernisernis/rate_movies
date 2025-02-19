package com.example.ratemovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ratemovies.app.App
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
                App()
            }
        }
    }
}