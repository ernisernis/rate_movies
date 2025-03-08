package com.example.ratemovies.movie.presentation.movie_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.ratemovies.app.Route
import com.example.ratemovies.core.domain.util.onSuccess
import com.example.ratemovies.movie.domain.model.Movie
import com.example.ratemovies.movie.domain.repository.MovieRepository
import com.example.ratemovies.movie.presentation.models.toMovieDetailUi
import com.example.ratemovies.movie.presentation.models.toMovieUi
import com.example.ratemovies.movie.presentation.movie_list.components.movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val movieId = savedStateHandle.toRoute<Route.MovieDetail>().id

    private val _state = MutableStateFlow(MovieDetailState())
    val state = _state
        .onStart {
            observeBookmarkStatus()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieDetailState()
        )

    fun onAction(action: MovieDetailAction) {
        when (action) {
            is MovieDetailAction.OnRateClick -> {
                // TODO
            }
            is MovieDetailAction.OnSelectedMovieChange -> {
                _state.update { it.copy(
                    movie = action.movie,
                    movieUi = action.movie.toMovieUi(),
                ) }
                viewModelScope.launch {
                    movieRepository
                        .getMovieDetail(id = movieId)
                        .onSuccess { movieDetail ->
                            _state.update { it.copy(
                                movie = state.value.movie?.copy(
                                    movieDetail = movieDetail
                                ),
                                movieUi = state.value.movieUi?.copy(
                                    movieDetailUi = movieDetail.toMovieDetailUi()
                                )
                            ) }
                        }
                }
            }
            is MovieDetailAction.OnBookmarkClick -> {
                if (state.value.movie?.movieDetail == null) return

                viewModelScope.launch {
                    if (state.value.isBookmarked) {
                        movieRepository.deleteFromBookmark(movieId)
                    } else {
                        state.value.movie?.let { movie ->
                           movieRepository.markAsBookmarked(movie)
                        }
                    }
                }
            }
        }
    }

    private fun observeBookmarkStatus() {
        viewModelScope.launch {
            movieRepository
                .isBookBookmarked(movieId)
                .onEach { isBookmarked ->
                    _state.update { it.copy(
                        isBookmarked = isBookmarked
                    ) }
                }
                .launchIn(viewModelScope)
        }
    }
}
