package com.edurda77.movie.ui.presentation

import androidx.recyclerview.widget.RecyclerView
import com.edurda77.movie.databinding.ItemGridViewBinding
import com.edurda77.movie.entity.MovieInList
import com.edurda77.movie.utils.colorRating

class MovieHolder(private val binding: ItemGridViewBinding):
    RecyclerView.ViewHolder(binding.root) {

    fun bind(moveInList: MovieInList) {
        binding.dateRelease.text = moveInList.dateRelease
        binding.titleMovie.text = moveInList.title
        binding.txtProgress.text = moveInList.rating.toString()
        binding.rang.progress = moveInList.rating
        binding.rang.setIndicatorColor(colorRating(moveInList.rating))
    }

}