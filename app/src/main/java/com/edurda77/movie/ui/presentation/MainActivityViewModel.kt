package com.edurda77.movie.ui.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.movie.entity.StructureOfTopMovie
import com.edurda77.movie.network.RepositoryMovieImpl
import com.edurda77.movie.utils.StateMainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RepositoryMovieImpl) :
    ViewModel() {
    var pageNumber = 1
    private var dataMovie: StructureOfTopMovie? = null
    private val _movieData =
        MutableLiveData<StateMainActivity>(StateMainActivity.Empty)
    val movieData = _movieData

    init {
        getTopMovie()
    }

    fun getTopMovie() {
        viewModelScope.launch {
            _movieData.postValue(StateMainActivity.Loading)
            //val response = repository.getTopMoviesForShow(pageNumber)
            _movieData.postValue(handleTopMovie(repository.getTopMoviesForShow(pageNumber)))
        }
    }

    private fun handleTopMovie(data: StructureOfTopMovie): StateMainActivity {
        return try {
            pageNumber++
            if (dataMovie==null) {
                dataMovie = data
            } else {
                dataMovie?.movieInList?.addAll(data.movieInList)
            }
            StateMainActivity.Success(dataMovie!!)
        } catch (error: Exception) {
            StateMainActivity.Failure(error.toString())
        }
    }
}