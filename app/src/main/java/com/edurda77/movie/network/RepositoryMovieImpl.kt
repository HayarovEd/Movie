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
        val inputCretits = apiService.getCreditsMovie(id, API_KEY, LANGUAGE)
        return dataToMovie(inputDetails, inputCretits)
    }

    private fun addToList(inputlist: JsonStructureTopMovies): List<MovieInList> {
        val list = mutableListOf<MovieInList>()
        inputlist.results.forEach {
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
        inputCretits: JsonStructureCreditsMovie
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
        cast = inputCretits.cast
    )

}