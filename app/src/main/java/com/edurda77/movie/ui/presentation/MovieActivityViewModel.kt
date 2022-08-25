package com.edurda77.movie.ui.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.movie.network.RepositoryMovieImpl
import com.edurda77.movie.utils.StateMovieActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieActivityViewModel @Inject constructor(private val repository: RepositoryMovieImpl) :
    ViewModel() {
    private val _movieData =
        MutableLiveData<StateMovieActivity>(StateMovieActivity.Empty)
    val movieData = _movieData

    fun getMovie(id : Int) {
        viewModelScope.launch {
            _movieData.value = StateMovieActivity.Loading
            try {
                _movieData.value = StateMovieActivity.Success(repository.getMovieisChoisedForShow(id))
            } catch (error: Exception) {
                _movieData.value = StateMovieActivity.Failure(error.toString())
            }
        }
    }
}