package com.edurda77.movie.ui.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.edurda77.movie.R

class MovieAdapter(private val list: List<String>, private val context: Context) : BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private lateinit var titleMovie: TextView
    private lateinit var rangMovie: TextView
    private lateinit var dateReleaseMovie: TextView
    private lateinit var posterMovie: ImageView

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
            //rangMovie = convertView.findViewById(R.id.rang)
            //dateReleaseMovie = convertView.findViewById(R.id.dateRelease)
            posterMovie = convertView.findViewById(R.id.poster)
        }
        titleMovie.text = list.get(position)
        return convertView!!
    }
}