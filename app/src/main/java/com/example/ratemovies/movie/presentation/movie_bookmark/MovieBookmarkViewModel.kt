package com.example.ratemovies.movie.presentation.movie_bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemovies.movie.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieBookmarkViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(MovieBookmarkState())
    val state = _state
        .onStart {
            observeBookmarks()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieBookmarkState()
        )

    fun onAction(action: MovieBookmarkAction) {
        when (action) {
            is MovieBookmarkAction.OnBookmarkClick -> {
                viewModelScope.launch {
                    movieRepository
                        .deleteFromBookmark(action.id)
                }
            }
            else -> Unit
        }
    }

    private fun observeBookmarks() {
        viewModelScope.launch {
            movieRepository
                .getBookmarks()
                .onEach { bookmarkMovies ->
                    _state.update { it.copy(
                        bookmarkMovies = bookmarkMovies
                    ) }
                }
                .launchIn(viewModelScope)
        }
    }
}