package com.example.ratemovies.movie.presentation.movie_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemovies.core.domain.util.onError
import com.example.ratemovies.core.domain.util.onSuccess
import com.example.ratemovies.movie.data.network.RemoteMovieDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieDataSource: RemoteMovieDataSource,
) : ViewModel() {
    private val _state = MutableStateFlow(MovieListState())
    val state = _state.asStateFlow()

    init {
        loadMovies()
    }

    fun onAction(action: MovieListAction) {
        when (action) {
            is MovieListAction.OnMovieClick -> {
                // TODO
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
                .getNowPlayingMovies()
                .onSuccess { nowPlayingMovies ->
                    _state.update {
                        it.copy(
                            nowPlayingMoviesUi = nowPlayingMovies,
                            isLoading = false,
                        )
                    }
                }
                .onError { error ->
                    Log.d("ERNIS33", "loadMovies: $error")
                }

            movieDataSource
                .getPopularMovies()
                .onSuccess { popularMovies ->
                    _state.update {
                        it.copy(
                            popularMoviesUi = popularMovies,
                            isLoading = false,
                        )
                    }
                }
                .onError { error ->
                    Log.d("ERNIS33", "loadMovies: $error")
                }

            movieDataSource
                .getUpcomingMovies()
                .onSuccess { upcomingMovies ->
                    _state.update {
                        it.copy(
                            upcomingMoviesUi = upcomingMovies,
                            isLoading = false,
                        )
                    }
                }
                .onError { error ->
                    Log.d("ERNIS33", "loadMovies: $error")
                }

            movieDataSource
                .getTopRatedMovies()
                .onSuccess { topRatedMovies ->
                    _state.update {
                        it.copy(
                            topRatedMoviesUi = topRatedMovies,
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