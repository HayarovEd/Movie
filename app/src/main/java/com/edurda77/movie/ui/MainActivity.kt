package com.edurda77.movie.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
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
import com.edurda77.movie.utils.QUERY_PAGE_SIZE
import com.edurda77.movie.utils.StateMainActivity
import com.edurda77.movie.utils.TRANSFER_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainActivityViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.movieData.observe(this) {
            when (it) {
                is StateMainActivity.Loading->{
                    binding.rvMovie.isVisible=true
                    showProgressBar()
                }
                is StateMainActivity.Failure -> {
                    binding.rvMovie.isVisible = false
                    binding.progressState.isVisible = false
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is StateMainActivity.Success->{
                    hideProgressBar()
                    binding.rvMovie.isVisible = true
                    initRecyclerView(it.data.movieInList)
                    val totalPages = it.data.totalPages / QUERY_PAGE_SIZE + 2
                    isLastPage = viewModel.pageNumber==totalPages
                }
                is StateMainActivity.Empty->{

                }
            }
        }

    }

    private fun showProgressBar() {
        binding.progressState.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideProgressBar() {
        binding.progressState.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun initRecyclerView(data: List<MovieInList>) {
        val recyclerView: RecyclerView = binding.rvMovie
        recyclerView.layoutManager = GridLayoutManager(
            this, 2, GridLayoutManager
                .VERTICAL, false
        )
        recyclerView.addOnScrollListener(this.scrollListener)
        val stateClickListener: MovieAdapter.OnStateClickListener =
            object : MovieAdapter.OnStateClickListener {
                override fun onStateClick(moveInList: MovieInList, position: Int) {
                    val intent = Intent(this@MainActivity, MovieActivity::class.java)
                    intent.putExtra(TRANSFER_ID, moveInList.id)
                    startActivity(intent)
                }
            }
        movieAdapter = MovieAdapter(stateClickListener)
        movieAdapter.differ.submitList(data)
        recyclerView.adapter = movieAdapter

    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if(shouldPaginate) {
                viewModel.getTopMovie()
                isScrolling = false
            } else {
                binding.rvMovie.setPadding(0, 0, 0, 0)
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

}