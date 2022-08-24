package com.edurda77.movie.ui

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.movie.databinding.ActivityMainBinding
import com.edurda77.movie.entity.MovieInList
import com.edurda77.movie.ui.presentation.MovieAdapter
import com.edurda77.movie.utils.TRANSPHER_ID

class MainActivity : AppCompatActivity() {
    private val courseList = mutableListOf<MovieInList>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        courseList.add(MovieInList(23, "gsgfgfgs1", 10, "12-12-2020", "dddd"))
        courseList.add(MovieInList(24, "gsgfgfgs2", 20, "12-12-2020", "dddd"))
        courseList.add(MovieInList(241, "gsgfgfgs3", 30, "12-12-2020", "dddd"))
        courseList.add(MovieInList(23, "gsgfgfgs4", 10, "12-12-2020", "dddd"))
        courseList.add(MovieInList(23, "gsgfgfgs5", 40, "12-12-2020", "dddd"))
        courseList.add(MovieInList(23, "gsgfgfgs6", 50, "12-12-2020", "dddd"))
        courseList.add(MovieInList(23, "gsgfgfgs7", 100, "12-12-2020", "dddd"))
        courseList.add(MovieInList(23, "gsgfgfgs8", 0, "12-12-2020", "dddd"))
        courseList.add(MovieInList(23, "gsgfgfgs9", 30, "12-12-2020", "dddd"))
        courseList.add(MovieInList(23, "gsgfgfgs10", 17, "12-12-2020", "dddd"))

        initRecyclerView(courseList)

    }

    private fun initRecyclerView(data: MutableList<MovieInList>) {
        val recyclerView: RecyclerView = binding.rvMovie
        recyclerView.layoutManager = GridLayoutManager(
            this, 2, GridLayoutManager
                .VERTICAL, false
        )
        val stateClickListener: MovieAdapter.OnStateClickListener =
            object : MovieAdapter.OnStateClickListener {
                override fun onStateClick(moveInList: MovieInList, position: Int) {
                    val intent = Intent(this@MainActivity, MovieActivity::class.java)
                    intent.putExtra(TRANSPHER_ID, moveInList.id)
                    startActivity(intent)
                }
            }
        recyclerView.adapter = MovieAdapter(data, stateClickListener)

    }
}