package com.example.ratemovies.movie.presentation.movie_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemovies.core.domain.util.onError
import com.example.ratemovies.core.domain.util.onSuccess
import com.example.ratemovies.core.navigation.Destination
import com.example.ratemovies.core.navigation.Navigator
import com.example.ratemovies.movie.domain.MovieDataSource
import com.example.ratemovies.movie.presentation.models.toMovieUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val movieDataSource: MovieDataSource,
    private val navigator: Navigator,
) : ViewModel() {
    private val _state = MutableStateFlow(MovieListState())
    val state = _state.asStateFlow()

    init {
        loadMovies()
    }

    fun onAction(action: MovieListAction) {
        when (action) {
            is MovieListAction.OnMovieClick -> {
                viewModelScope.launch {
                    navigator.navigate(
                        destination = Destination.DetailScreen(action.movieUi.banner),
                    )
                }
            }
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true,
                )
            }

            movieDataSource
                .getMovies()
                .onSuccess { movies ->
                    _state.update {
                        it.copy(
                            moviesUi = movies.map { it.toMovieUi() },
                            isLoading = false,
                        )
                    }
                }
                .onError { error ->
                    Log.d("ERNIS33", "loadMovies: $error")
                }
        }
    }
}