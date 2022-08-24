package com.edurda77.movie.ui.presentation

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.edurda77.movie.R
import com.edurda77.movie.ui.entity.MovieInList
import com.google.android.material.progressindicator.CircularProgressIndicator

class MovieAdapter(private val list: List<MovieInList>, private val context: Context) : BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private lateinit var titleMovie: TextView
    private lateinit var rangMovie: TextView
    private lateinit var dateReleaseMovie: TextView
    private lateinit var posterMovie: ImageView
    private lateinit var rangMovieProgress: CircularProgressIndicator

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View {
        /*val convertView = convertView
        if (convertView == null) {
            val binding = ItemGridViewBinding.inflate(LayoutInflater.from(context), convertView,false)
            binding.dateRelease.text = list[position].dateRelease
            binding.titleMovie.text = list[position].title
            binding.txtProgress.text = list[position].rating.toString()
            binding.rang.progress = list[position].rating
        }*/

        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.item_grid_view, null)
        }
        if (convertView != null) {
            titleMovie = convertView.findViewById(R.id.titleMovie)
            rangMovie = convertView.findViewById(R.id.txtProgress)
            dateReleaseMovie = convertView.findViewById(R.id.dateRelease)
            posterMovie = convertView.findViewById(R.id.poster)
            rangMovieProgress = convertView.findViewById(R.id.rang)
        }
        titleMovie.text = list[position].title
        dateReleaseMovie.text = list[position].dateRelease
        rangMovie.text = list[position].rating.toString()
        rangMovieProgress.progress = list[position].rating
        rangMovieProgress.setIndicatorColor(colorRating(list[position].rating))

        return convertView!!
    }
    private fun colorRating (rating: Int): Int {
        return if (rating<=15) {
            Color.RED
        } else if (rating in 16..40) {
            Color.YELLOW
        } else if (rating in 41..60) {
            Color.BLUE
        } else Color.GREEN
    }
}
