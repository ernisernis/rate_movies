package com.example.ratemovies.movie.domain

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)