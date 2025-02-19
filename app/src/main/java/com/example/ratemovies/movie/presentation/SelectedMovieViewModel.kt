package com.example.ratemovies.movie.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ratemovies.movie.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

// Shared ViewModel scoped to Route.MovieGraph
@HiltViewModel
class SelectedMovieViewModel @Inject constructor(
): ViewModel() {

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie = _selectedMovie.asStateFlow()

    fun onSelectMovie(movie: Movie?) {
        _selectedMovie.value = movie
    }
}