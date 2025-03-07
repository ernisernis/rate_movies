package com.example.ratemovies.movie.domain.util

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null,
)