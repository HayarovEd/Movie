package com.edurda77.movie.ui

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.edurda77.movie.databinding.ActivityMainBinding
import com.edurda77.movie.ui.entity.MovieInList
import com.edurda77.movie.ui.presentation.MovieAdapter

class MainActivity : AppCompatActivity() {
    lateinit var courseGRV: GridView
    private val courseList = mutableListOf<MovieInList>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        courseGRV = binding.gridView
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

        val courseAdapter = MovieAdapter(courseList, this@MainActivity)
        courseGRV.adapter = courseAdapter
        courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this@MainActivity, MovieActivity::class.java)
            intent.putExtra(String(), courseList[position].id)
            startActivity(intent)
        }
    }
}