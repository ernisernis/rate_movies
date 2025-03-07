package com.example.ratemovies.movie.presentation.movie_rate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemovies.movie.data.mappers.toRating
import com.example.ratemovies.movie.domain.model.Movie
import com.example.ratemovies.movie.domain.repository.MovieRepository
import com.example.ratemovies.movie.presentation.models.toMovieUi
import com.example.ratemovies.movie.presentation.models.use_case.ValidateRateDescription
import com.example.ratemovies.movie.presentation.models.use_case.ValidateRateNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieRateViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val validateRateDescription: ValidateRateDescription,
    private val validateRateNumber: ValidateRateNumber,
) : ViewModel() {

    private val _state = MutableStateFlow(MovieRateState())
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MovieRateState()
        )

    fun onAction(action: MovieRateAction) {
        when (action) {
            is MovieRateAction.OnMovieRateClick -> {
                _state.update {
                    it.copy(
                        selectedIndex = action.index,
                        indexError = null,
                    )
                }
            }
            is MovieRateAction.OnMovieRateSubmit -> submitRate(action.movie)

            is MovieRateAction.OnSelectedMovieChange -> {
                // TODO: GetMovieRating
                _state.update { it.copy(
                    movie = action.movie,
                    movieUi = action.movie.toMovieUi()
                ) }
            }
            is MovieRateAction.OnDescriptionChange -> {
                _state.update { it.copy(
                    description = action.text
                ) }
            }
            else -> Unit
        }
    }

    private fun submitRate(movie: Movie) {
        val descriptionResult = validateRateDescription.execute(state.value.description)
        val numberResult = validateRateNumber.execute(state.value.selectedIndex)

        val hasError = listOf(
            descriptionResult,
            numberResult,
        ).any { !it.successful }

        if (hasError) {
            _state.update {
                it.copy(
                    descriptionError = descriptionResult.errorMessage,
                    indexError = numberResult.errorMessage,
                )
            }
        } else {
            viewModelScope.launch {
                val rating = movie.toRating().copy(
                    description = state.value.description.trim().ifEmpty { null },
                    userRating = state.value.selectedIndex,
                )
                // TODO:
            }
        }
    }
}