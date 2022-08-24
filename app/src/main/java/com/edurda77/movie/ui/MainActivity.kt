package com.edurda77.movie.ui

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.edurda77.movie.databinding.ActivityMainBinding
import com.edurda77.movie.ui.presentation.MovieAdapter

class MainActivity : AppCompatActivity() {
    lateinit var courseGRV: GridView
    lateinit var courseList: ArrayList<String>
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        courseGRV = binding.gridView
        courseList = ArrayList<String>()
        courseList.add("gsgfgfgs1")
        courseList.add("gsgfgfgs2")
        courseList.add("gsgfgfgs3")
        courseList.add("gsgfgfgs4")
        courseList.add("gsgfgfgs5")
        courseList.add("gsgfgfgs6")
        courseList.add("gsgfgfgs7")
        courseList.add("gsgfgfgs8")
        courseList.add("gsgfgfgs9")

        val courseAdapter = MovieAdapter(courseList, this@MainActivity)
        courseGRV.adapter = courseAdapter
    }
}