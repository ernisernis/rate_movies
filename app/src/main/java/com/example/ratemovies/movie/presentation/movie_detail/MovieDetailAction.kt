package com.example.ratemovies.movie.presentation.movie_detail

interface MovieDetailAction {
    data class OnTestClick(val id: String) : MovieDetailAction
}