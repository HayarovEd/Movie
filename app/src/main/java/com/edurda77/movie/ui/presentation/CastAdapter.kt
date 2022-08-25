package com.edurda77.movie.ui.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.movie.databinding.ItemCastBinding
import com.edurda77.movie.databinding.ItemGridViewBinding
import com.edurda77.movie.entity.Cast
import com.edurda77.movie.entity.MovieInList

class CastAdapter(
    private val list: List<Cast>
) : RecyclerView.Adapter<CastHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CastHolder(ItemCastBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(holder: CastHolder, position: Int) {
        val castList = list[position]
        holder.bind(castList)
    }

    override fun getItemCount(): Int = list.size

}
