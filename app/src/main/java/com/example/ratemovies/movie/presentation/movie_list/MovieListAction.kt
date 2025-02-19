package com.example.ratemovies.movie.presentation.movie_list

import com.example.ratemovies.movie.domain.Movie


interface MovieListAction {
    data class OnMovieClick(val movie: Movie): MovieListAction
}