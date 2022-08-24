package com.edurda77.movie.ui.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edurda77.movie.network.RepositoryMovieImpl
import com.edurda77.movie.utils.StateMainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RepositoryMovieImpl) :
    ViewModel() {
    private val _movieData =
        MutableLiveData<StateMainActivity>(StateMainActivity.Empty)
    val movieData = _movieData

    fun getDataForShow(pageNumber: Int) {
        viewModelScope.launch {
            _movieData.value = StateMainActivity.Loading
            try {
                _movieData.value =
                    StateMainActivity.Success(repository.getTopMoviesForShow(pageNumber))
            } catch (error: Exception) {
                _movieData.value = StateMainActivity.Failure(error.toString())
            }
        }
    }
}