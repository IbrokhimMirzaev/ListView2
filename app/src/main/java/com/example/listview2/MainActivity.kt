package com.example.listview2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listview2.adapter.MovieAdapter
import com.example.listview2.databinding.ActivityMainBinding
import com.example.listview2.databinding.MovieItemBinding
import com.example.listview2.model.movie.Movie

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movies = mutableListOf<Movie>()

        for (i in 1 .. 100) {
            movies.add(Movie("Spider Man $i", "https://picsum.photos/200", false))
        }

        val arrayAdapter = MovieAdapter(this, movies)
        binding.lv.adapter = arrayAdapter

        binding.favBtn.setOnClickListener {
            var favouriteMovies = movies.filter { it.isFavourite }
            val adapter = MovieAdapter(this, favouriteMovies)
            binding.lv.adapter = adapter
        }

        binding.homeBtn.setOnClickListener {
            val ad = MovieAdapter(this, movies)
            binding.lv.adapter = ad
        }
    }
}