package com.example.ratemovies.movie.presentation.movie_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemovies.core.domain.util.onError
import com.example.ratemovies.core.domain.util.onSuccess
import com.example.ratemovies.movie.domain.MovieDataSource
import com.example.ratemovies.movie.domain.MovieNavArgs
import com.example.ratemovies.movie.presentation.models.toMovieDetailsUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieDataSource: MovieDataSource,
) : ViewModel() {
    private val _state = MutableStateFlow(MovieDetailsState())
    val state = _state.asStateFlow()

    fun initData(movieNavArgs: MovieNavArgs) {
        _state.update {
            it.copy(
                bannerUrl = movieNavArgs.bannerUrl,
                title = movieNavArgs.title,
                imageUrl = movieNavArgs.imageUrl,
            )
        }
        viewModelScope.launch {
            movieDataSource
                .getMovieDetails(movieNavArgs.id)
                .onSuccess { movieDetails ->
                    Log.d("ERNIS33", "initData: $movieDetails")
                    _state.update {
                        it.copy(
                            movieDetailsUi = movieDetails.toMovieDetailsUi(),
                        )
                    }
                }
                .onError { error ->
                    Log.d("ERNIS33", "initData: $error")
                }
        }
    }

    fun onAction(action: MovieDetailsAction) {
        when (action) {
            is MovieDetailsAction.OnRateClick -> {
                // TODO
            }
        }
    }
}
