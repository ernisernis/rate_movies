package com.example.ratemovies.movie.presentation.movie_rate

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ratemovies.movie.domain.MovieNavArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieRateViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(MovieRateState())
    val state = _state.asStateFlow()

    fun initData(movieNavArgs: MovieNavArgs) {
        _state.update {
            it.copy(
                bannerUrl = movieNavArgs.bannerUrl,
                title = movieNavArgs.title,
                imageUrl = movieNavArgs.imageUrl,
            )
        }
    }

    fun onAction(action: MovieRateAction) {
        when (action) {
            is MovieRateAction.OnRateClick -> {
                _state.update {
                    it.copy(
                        selectedIndex = action.index,
                    )
                }
            }

            MovieRateAction.OnRateSubmit -> {
                Log.d("ERNIS33", "onAction: ${state.value.selectedIndex}")
            }
        }
    }
}