package com.example.ratemovies.movie.presentation.movie_details

interface MovieDetailsAction {
    data class OnTestClick(val id: String) : MovieDetailsAction
}