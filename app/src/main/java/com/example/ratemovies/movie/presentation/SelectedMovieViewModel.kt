package com.example.ratemovies.movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemovies.core.domain.util.onError
import com.example.ratemovies.core.domain.util.onSuccess
import com.example.ratemovies.movie.domain.model.Movie
import com.example.ratemovies.movie.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Shared ViewModel scoped to Route.MovieGraph
@HiltViewModel
class SelectedMovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
): ViewModel() {

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie = _selectedMovie.asStateFlow()

    fun onSelectMovie(movie: Movie?) {
        _selectedMovie.value = movie
    }

    fun onSelectMovieId(id: Int) {
        viewModelScope.launch {
            movieRepository
                .getMovie(id)
                .onSuccess { movie ->
                    _selectedMovie.value = movie
                }
                .onError {
                    // TODO implement
                }
        }
    }

}