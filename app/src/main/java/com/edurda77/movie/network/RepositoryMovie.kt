package com.edurda77.movie.network

import com.edurda77.movie.entity.MovieIsShow
import com.edurda77.movie.entity.StructureOfTopMovie

interface RepositoryMovie {
    suspend fun getTopMoviesForShow (page: Int) : StructureOfTopMovie
    suspend fun getMovieisChoisedForShow(id: Int) : MovieIsShow
}