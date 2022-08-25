package com.edurda77.movie.entity

data class StructureOfTopMovie(
    val totalPages: Int,
    val movieInList: MutableList<MovieInList>
)
