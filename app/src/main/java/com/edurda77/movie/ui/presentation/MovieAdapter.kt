package com.edurda77.movie.ui.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.movie.databinding.ItemGridViewBinding
import com.edurda77.movie.entity.MovieInList

class MovieAdapter(
    private val list: List<MovieInList>,
    private val onClickListener: OnStateClickListener
) :
    RecyclerView.Adapter<MovieHolder>() {
    interface OnStateClickListener {
        fun onStateClick(moveInList: MovieInList, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieHolder(ItemGridViewBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        val moveInList: MovieInList = list[position]
        holder.bind(moveInList)

        holder.itemView.setOnClickListener {
            onClickListener.onStateClick(moveInList, position)
        }
    }

    override fun getItemCount(): Int = list.size
}
