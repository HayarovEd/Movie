package com.edurda77.movie.network

import com.edurda77.movie.entity.*
import com.edurda77.movie.utils.API_KEY
import com.edurda77.movie.utils.LANGUAGE
import javax.inject.Inject

class RepositoryMovieImpl @Inject constructor(private val apiService: ApiService) :
    RepositoryMovie {
    override suspend fun getTopMoviesForShow(page: Int): StructureOfTopMovie {
        val inputList = apiService.getTopRated(API_KEY, LANGUAGE, page)
        return StructureOfTopMovie(inputList.totalPages, addToList(inputList))
    }

    override suspend fun getMovieisChoisedForShow(id: Int): MovieIsShow {
        val inputDetails = apiService.getDetailsMovie(id, API_KEY, LANGUAGE)
        val inputCredits = apiService.getCreditsMovie(id, API_KEY, LANGUAGE)
        return dataToMovie(inputDetails, inputCredits)
    }

    private fun addToList(inputList: JsonStructureTopMovies): MutableList<MovieInList> {
        val list = mutableListOf<MovieInList>()
        inputList.results.forEach {
            list.add(
                MovieInList(
                    id = it.id,
                    title = it.title,
                    rating = it.popularity,
                    dateRelease = it.releaseDate,
                    pathPoster = it.posterPath
                )
            )
        }
        return list
    }

    private fun dataToMovie(
        inputDetails: JsonStructureDetailsMovie,
        inputCredits: JsonStructureCreditsMovie
    ) = MovieIsShow(
        id = inputDetails.id,
        title = inputDetails.title,
        posterPath = inputDetails.posterPath,
        backdropPath = inputDetails.backdropPath,
        releaseDate = inputDetails.releaseDate,
        runtime = inputDetails.runtime,
        popularity = inputDetails.popularity,
        overview = inputDetails.overview,
        genres = inputDetails.genres,
        cast = inputCredits.cast
    )

}