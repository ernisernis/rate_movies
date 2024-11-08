package com.example.ratemovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.ratemovies.movie.presentation.movie_list.MovieListScreen
import com.example.ratemovies.movie.presentation.movie_list.MovieListState
import com.example.ratemovies.movie.presentation.movie_list.components.previewMovie
import com.example.ratemovies.ui.theme.RateMoviesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RateMoviesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MovieListScreen(
                        modifier = Modifier
                            .padding(innerPadding),
                        state = MovieListState(
                            moviesUi = (1..20).map {
                                previewMovie.copy(id = it)
                            }
                        )
                    )
                }
            }
        }
    }
}