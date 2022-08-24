package com.edurda77.movie.ui.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.movie.R
import com.edurda77.movie.databinding.ItemGridViewBinding
import com.edurda77.movie.entity.MovieInList
import com.edurda77.movie.utils.BASE_URL_POSTER
import com.edurda77.movie.utils.colorRating
import kotlin.math.roundToInt

class MovieHolder(private val binding: ItemGridViewBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(moveInList: MovieInList) {
        binding.dateRelease.text = moveInList.dateRelease
        binding.titleMovie.text = moveInList.title
        binding.txtProgress.text = moveInList.rating.toString()
        binding.rang.progress = moveInList.rating.roundToInt()
        binding.rang.setIndicatorColor(colorRating(moveInList.rating.roundToInt()))
        val urlPoster = BASE_URL_POSTER+moveInList.pathPoster
        Glide.with(this.itemView.context)
            .load(urlPoster)
            .placeholder(R.drawable.ic_movie_poster)
            .into(binding.poster)
    }

}