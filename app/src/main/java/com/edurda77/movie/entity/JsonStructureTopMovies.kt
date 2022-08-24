package com.edurda77.movie.entity


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JsonStructureTopMovies(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) : Serializable