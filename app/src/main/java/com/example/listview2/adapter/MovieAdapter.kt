package com.example.listview2.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import coil.load
import com.example.listview2.R
import com.example.listview2.databinding.MovieItemBinding
import com.example.listview2.model.movie.Movie

class MovieAdapter(context: Context, var movies: MutableList<Movie>) :
    ArrayAdapter<Movie>(context, R.layout.movie_item, movies) {

    override fun getCount(): Int {
        return movies.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val movieItem: MovieItemBinding
        if (convertView == null) {
            movieItem = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            Log.d("TAG", "create: $position")
        } else {
            movieItem = MovieItemBinding.bind(convertView)
            Log.d("TAG", "update: $position")
        }

        val movie = movies[position]

        movieItem.name.text = movie.name
        movieItem.imgView.load(movie.img) {
            placeholder(R.drawable.empty)
        }



        movieItem.like.setOnClickListener {
            movie.isFavourite = !movie.isFavourite

            if (movie.isFavourite) {
                movieItem.like.setBackgroundResource(R.drawable.favourite)
            } else {
                movieItem.like.setBackgroundResource(R.drawable.favourite_border)
            }
        }

        return movieItem.root
    }
}