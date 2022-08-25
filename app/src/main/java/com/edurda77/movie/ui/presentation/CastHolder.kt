package com.edurda77.movie.ui.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.movie.R
import com.edurda77.movie.databinding.ItemCastBinding
import com.edurda77.movie.databinding.ItemGridViewBinding
import com.edurda77.movie.entity.Cast
import com.edurda77.movie.entity.MovieInList
import com.edurda77.movie.utils.BASE_URL_POSTER
import com.edurda77.movie.utils.colorRating
import com.edurda77.movie.utils.popularityConvertor

class CastHolder(private val binding: ItemCastBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cast: Cast) {

        binding.castName.text = cast.name
        binding.castRole.text = cast.character
        val urlPoster = BASE_URL_POSTER+cast.profilePath
        Glide.with(this.itemView.context)
            .load(urlPoster)
            .placeholder(R.drawable.ic_movie_poster)
            .into(binding.posterCast)
    }

}