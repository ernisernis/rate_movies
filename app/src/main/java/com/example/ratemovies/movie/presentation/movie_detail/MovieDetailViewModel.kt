package com.example.ratemovies.movie.presentation.movie_detail

import androidx.lifecycle.ViewModel
import com.example.ratemovies.core.navigation.Navigator
import com.example.ratemovies.movie.domain.MovieDataSource
import com.example.ratemovies.movie.domain.MovieNavArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieDetailViewModel(
    private val movieDataSource: MovieDataSource,
    private val navigator: Navigator,
) : ViewModel() {
    private val _state = MutableStateFlow(MovieDetailState())
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

    fun onAction(action: MovieDetailAction) {
        when (action) {
            is MovieDetailAction.OnTestClick -> {
            }
        }
    }
}
