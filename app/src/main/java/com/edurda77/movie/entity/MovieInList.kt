package com.edurda77.movie.entity

import java.io.Serializable

data class MovieInList(
    val id: Int,
    val title: String,
    val rating: Double,
    val dateRelease: String,
    val pathPoster: String
): Serializable
