package com.edurda77.movie.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edurda77.movie.databinding.ActivityMainBinding
import com.edurda77.movie.entity.MovieInList
import com.edurda77.movie.ui.presentation.MainActivityViewModel
import com.edurda77.movie.ui.presentation.MovieAdapter
import com.edurda77.movie.utils.StateMainActivity
import com.edurda77.movie.utils.TRANSFER_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()
    //private val courseList = mutableListOf<MovieInList>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getDataForShow(1)
        viewModel.movieData.observe(this) {
            when (it) {
                is StateMainActivity.Loading->{
                    binding.rvMovie.isVisible=true
                    binding.progressState.isVisible=true
                }
                is StateMainActivity.Failure -> {
                    binding.rvMovie.isVisible = false
                    binding.progressState.isVisible = false
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is StateMainActivity.Success->{
                    binding.rvMovie.isVisible = true
                    binding.progressState.isVisible = false
                    initRecyclerView(it.data.movieInList)
                }
                is StateMainActivity.Empty->{

                }
            }
        }

    }

    private fun initRecyclerView(data: List<MovieInList>) {
        val recyclerView: RecyclerView = binding.rvMovie
        recyclerView.layoutManager = GridLayoutManager(
            this, 2, GridLayoutManager
                .VERTICAL, false
        )
        val stateClickListener: MovieAdapter.OnStateClickListener =
            object : MovieAdapter.OnStateClickListener {
                override fun onStateClick(moveInList: MovieInList, position: Int) {
                    val intent = Intent(this@MainActivity, MovieActivity::class.java)
                    intent.putExtra(TRANSFER_ID, moveInList.id)
                    startActivity(intent)
                }
            }
        recyclerView.adapter = MovieAdapter(data, stateClickListener)

    }
}