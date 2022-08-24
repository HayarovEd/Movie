package com.edurda77.movie.entity


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductionCompany(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
) : Serializable