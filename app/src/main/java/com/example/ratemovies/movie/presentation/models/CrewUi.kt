package com.example.ratemovies.movie.presentation.models

import com.example.ratemovies.movie.domain.model.Crew

data class CrewUi(
    val id: Int,
    val name: String,
    val job: String,
    val profilePath: String,
)

fun Crew.toCrewUi(): CrewUi {
    return CrewUi(
        id = id,
        name = name,
        job = job,
        profilePath = profilePath,
    )
}
