package com.example.ratemovies.movie.presentation.models.use_case

import com.example.ratemovies.movie.domain.ValidationResult


class ValidateRateDescription {

    fun execute(description: String): ValidationResult {
        if (description.trim().length > 10_000) {
            return ValidationResult(
                successful = false,
                errorMessage = "The description should be less than 10.000 characters!"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}