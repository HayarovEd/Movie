package com.edurda77.movie.entity

data class MovieIsShow(
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val runtime: Int,
    val popularity: Double,
    val overview: String,
    val genres: List<Genre>,
    val cast: List<Cast>
)