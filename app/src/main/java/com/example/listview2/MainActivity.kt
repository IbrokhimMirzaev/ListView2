package com.example.listview2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.listview2.adapter.MovieAdapter
import com.example.listview2.databinding.ActivityMainBinding
import com.example.listview2.model.movie.Movie

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movies = mutableListOf<Movie>()

        for (i in 1 .. 100) {
            movies.add(Movie("Spider Man $i", "https://cdn.marvel.com/content/1x/spidermannwh_hardcover.jpg", false))
        }

        val arrayAdapter = MovieAdapter(this, movies)
        binding.lv.adapter = arrayAdapter



//        binding.lv.setOnItemClickListener { adapterView, view, i, l ->
//
//        }
    }
}