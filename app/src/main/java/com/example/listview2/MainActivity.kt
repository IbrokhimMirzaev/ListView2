package com.example.listview2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
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
        var isSearch = true

        for (i in 1 .. 100) {
            movies.add(Movie("Spider Man $i", "https://picsum.photos/200", false))
        }

        val arrayAdapter = MovieAdapter(this, movies)
        binding.lv.adapter = arrayAdapter

        binding.favBtn.setOnClickListener {
            var favouriteMovies = movies.filter { it.isFavourite }.toMutableList()
            val adapter = MovieAdapter(this, favouriteMovies)
            binding.lv.adapter = adapter
        }

        binding.homeBtn.setOnClickListener {
            val ad = MovieAdapter(this, movies)
            binding.lv.adapter = ad
        }

        binding.searchBtn.setOnClickListener {
            if (isSearch) {
                binding.favBtn.visibility = View.GONE
                binding.homeBtn.visibility = View.GONE

                binding.searchBtn.setBackgroundResource(R.drawable.left)
                binding.editText.visibility = View.VISIBLE
                isSearch = false

                binding.editText.addTextChangedListener {
                    if (binding.editText.text.toString().isNotEmpty()) {
                        var filterMovies: MutableList<Movie> = mutableListOf()
                        for (i in movies) {
                            if (i.name.lowercase().contains(binding.editText.text.toString().lowercase())) {
                                filterMovies.add(i)
                            }
                        }
                        val filter = MovieAdapter(this, filterMovies)
                        binding.lv.adapter = filter
                    }
                    else {
                        val ad = MovieAdapter(this, movies)
                        binding.lv.adapter = ad
                    }
                }
            }
            else {
                binding.favBtn.visibility = View.VISIBLE
                binding.homeBtn.visibility = View.VISIBLE

                binding.searchBtn.setBackgroundResource(R.drawable.search)
                binding.editText.visibility = View.GONE
                isSearch = true
            }
        }

        binding.lv.setOnItemClickListener { adapterView, view, i, l ->
            var intent = Intent(this, EditActivity::class.java)
            intent.putExtra("index", i)
            intent.putExtra("name", movies[i].name)
            startActivity(intent)
        }
    }
}