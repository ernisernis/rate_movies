package com.example.ratemovies.movie.presentation.movie_detail

import androidx.lifecycle.ViewModel
import com.example.ratemovies.core.navigation.Navigator
import com.example.ratemovies.movie.domain.MovieDataSource
import com.example.ratemovies.movie.presentation.models.MovieUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieDetailViewModel(
    private val movieDataSource: MovieDataSource,
    private val navigator: Navigator,
) : ViewModel() {
    private val _state = MutableStateFlow(MovieDetailState())
    val state = _state.asStateFlow()

    init {
        "Hello"
    }

    fun initData(movieUi: MovieUi) {
        _state.update {
            it.copy(
                bannerUrl = movieUi.banner,
                title = movieUi.title,
                imageUrl = movieUi.imageUrl,
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
