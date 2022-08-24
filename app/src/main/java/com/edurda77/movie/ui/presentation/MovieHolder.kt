package com.edurda77.movie.ui.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.movie.R
import com.edurda77.movie.databinding.ItemGridViewBinding
import com.edurda77.movie.entity.MovieInList
import com.edurda77.movie.utils.BASE_URL_POSTER
import com.edurda77.movie.utils.colorRating
import com.edurda77.movie.utils.popularityConvertor

class MovieHolder(private val binding: ItemGridViewBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(moveInList: MovieInList) {
        binding.dateRelease.text = moveInList.dateRelease
        binding.titleMovie.text = moveInList.title
        val ratingInt = popularityConvertor(moveInList.rating)
        binding.txtProgress.text = ratingInt.toString()
        binding.rang.progress = ratingInt
        binding.rang.setIndicatorColor(colorRating(ratingInt))
        val urlPoster = BASE_URL_POSTER+moveInList.pathPoster
        Glide.with(this.itemView.context)
            .load(urlPoster)
            .placeholder(R.drawable.ic_movie_poster)
            .into(binding.poster)
    }

}