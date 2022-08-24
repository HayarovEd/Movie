package com.edurda77.movie.network

import com.edurda77.movie.entity.JsonStructureCreditsMovie
import com.edurda77.movie.entity.JsonStructureDetailsMovie
import com.edurda77.movie.entity.JsonStructureTopMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") apiKey: String,
        @Query("language") lang: String,
        @Query("page") page: Int
    ): JsonStructureTopMovies

    @GET("/movie/{movie_id}")
    suspend fun getDetailsMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String
    ): JsonStructureDetailsMovie

    @GET("/movie/{movie_id}/credits")
    suspend fun getCreditsMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") lang: String
    ): JsonStructureCreditsMovie
}