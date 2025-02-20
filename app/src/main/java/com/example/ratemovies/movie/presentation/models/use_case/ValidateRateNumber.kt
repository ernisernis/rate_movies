package com.example.ratemovies.movie.presentation.models.use_case

import com.example.ratemovies.movie.domain.ValidationResult

class ValidateRateNumber {

    fun execute(number: Int): ValidationResult {
        if (number == 0) {
            return ValidationResult(
                successful = false,
                errorMessage = "Please provide a rating!"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}