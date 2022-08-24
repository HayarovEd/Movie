package com.edurda77.movie.entity


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String
) : Serializable