package com.example.ratemovies.movie.presentation.movie_list

import com.example.ratemovies.movie.presentation.models.MovieUi

interface MovieListAction {
    data class OnMovieClick(val movieUi: MovieUi): MovieListAction
}