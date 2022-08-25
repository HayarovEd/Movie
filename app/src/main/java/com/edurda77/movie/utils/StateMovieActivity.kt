package com.edurda77.movie.utils

import com.edurda77.movie.entity.MovieIsShow

sealed class StateMovieActivity {
    object Loading : StateMovieActivity()
    class Failure(val error:String) : StateMovieActivity()
    class Success(val data: MovieIsShow) : StateMovieActivity()
    object Empty : StateMovieActivity()
}
