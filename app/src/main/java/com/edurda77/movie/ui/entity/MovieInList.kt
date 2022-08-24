package com.edurda77.movie.ui.entity

import java.io.Serializable

data class MovieInList(
    val id: Int,
    val title: String,
    val rating: Int,
    val dateRelease: String,
    val pathPoster: String
): Serializable
