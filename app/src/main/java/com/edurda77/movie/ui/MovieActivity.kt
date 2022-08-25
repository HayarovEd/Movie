package com.edurda77.movie.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edurda77.movie.R
import com.edurda77.movie.databinding.ActivityMovieBinding
import com.edurda77.movie.entity.Cast
import com.edurda77.movie.ui.presentation.CastAdapter
import com.edurda77.movie.ui.presentation.MovieActivityViewModel
import com.edurda77.movie.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {
    private val viewModel by viewModels<MovieActivityViewModel>()
    private lateinit var binding: ActivityMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMovieBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val arguments = intent.extras
        if (arguments!=null) {
            val id = arguments.getInt(TRANSFER_ID)
            viewModel.getMovie(id)
            viewModel.movieData.observe(this) {
                when (it) {
                    is StateMovieActivity.Loading->{
                        binding.progressStateMovie.isVisible = true
                    }
                    is StateMovieActivity.Failure -> {
                        binding.progressStateMovie.isVisible = false
                        Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                    }
                    is StateMovieActivity.Success->{
                        binding.progressStateMovie.isVisible = false
                        val posterUrl = BASE_URL_POSTER + it.data.posterPath
                        val backgroundPosterUrl = BASE_URL_POSTER + it.data.backdropPath
                        getPoster(binding.posterMovieThis, posterUrl)
                        //binding.view.background = getPoster()
                        binding.titleMovieThis.text = it.data.title
                        binding.releasedMovieThis.text = "Релиз: ${it.data.releaseDate}"
                        binding.timeMovieThis.text = minutesToHours(it.data.runtime)
                        binding.genreMovieThis.text = ganreToString(it.data.genres)
                        val ratingInt = popularityConvertor(it.data.popularity)
                        binding.txtProgressMovieThis.text = ratingInt.toString()
                        binding.rangMovieThis.progress = ratingInt
                        binding.rangMovieThis.setIndicatorColor(colorRating(ratingInt))
                        binding.overViewMovieThis.text = it.data.overview
                        binding.overViewMovieThis.movementMethod = ScrollingMovementMethod()
                        initRecyclerView(it.data.cast)
                    }
                    is StateMovieActivity.Empty->{

                    }
                }
            }
        }
    }

    private fun initRecyclerView(cast: List<Cast>) {
        val recyclerView: RecyclerView = binding.rvCasts
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )
        recyclerView.adapter = CastAdapter(cast)

    }

    private fun getPoster(imageView: ImageView, url: String) {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_movie_poster)
            .into(imageView)
    }
}