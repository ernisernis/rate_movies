package com.example.ratemovies.movie.presentation.movie_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemovies.core.domain.util.onError
import com.example.ratemovies.core.domain.util.onSuccess
import com.example.ratemovies.movie.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(MovieListState())
    val state = _state.asStateFlow()

    init {
        loadMovies()
    }

    fun onAction(action: MovieListAction) {
        when (action) {
            else -> Unit
        }
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _state.update { it.copy(loadingState = LoadingState.Loading) }
            movieRepository
                .getNowPlayingMovies()
                .onSuccess { nowPlayingMovies ->
                    _state.update { it.copy(
                        nowPlayingMoviesUi = nowPlayingMovies,
                        loadingState = LoadingState.Success,
                    ) }
                }
                .onError { error ->
                    Log.d("ERNIS33", "loadMovies: $error")
                }

            movieRepository
                .getPopularMovies()
                .onSuccess { popularMovies ->
                    _state.update { it.copy(
                        popularMoviesUi = popularMovies,
                        loadingState = LoadingState.Success,
                    ) }
                }
                .onError { error ->
                    Log.d("ERNIS33", "loadMovies: $error")
                }

            movieRepository
                .getUpcomingMovies()
                .onSuccess { upcomingMovies ->
                    _state.update { it.copy(
                        upcomingMoviesUi = upcomingMovies,
                        loadingState = LoadingState.Success,
                    ) }
                }
                .onError { error ->
                    Log.d("ERNIS33", "loadMovies: $error")
                }

            movieRepository
                .getTopRatedMovies()
                .onSuccess { topRatedMovies ->
                    _state.update { it.copy(
                        topRatedMoviesUi = topRatedMovies,
                        loadingState = LoadingState.Success,
                    ) }
                }
                .onError { error ->
                    Log.d("ERNIS33", "loadMovies: $error")
                }
        }
    }
}