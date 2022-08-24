package com.edurda77.movie.utils

import com.edurda77.movie.entity.StructureOfTopMovie

sealed class StateMainActivity {
    object Loading : StateMainActivity()
    class Failure(val error:String) : StateMainActivity()
    class Success(val data: StructureOfTopMovie) : StateMainActivity()
    object Empty : StateMainActivity()
}
