package com.example.listview2.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import com.example.listview2.R
import com.example.listview2.databinding.MovieItemBinding
import com.example.listview2.model.movie.Movie

class MovieAdapter(context: Context, var movies: MutableList<Movie>) :
    ArrayAdapter<Movie>(context, R.layout.movie_item, movies) {

    override fun getCount(): Int {
        return movies.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: MovieItemBinding
        if (convertView == null) {
            binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            binding = MovieItemBinding.bind(convertView)
        }

        val movie = movies[position]

        binding.name.text = movie.name
        binding.imgView.load(movie.img) {
            placeholder(R.drawable.empty)
            transformations(CircleCropTransformation())
        }

        if (movie.isFavourite) {
            binding.like.setBackgroundResource(R.drawable.favourite)
        } else {
            binding.like.setBackgroundResource(R.drawable.favourite_border)
        }



        binding.like.setOnClickListener {
            movie.isFavourite = !movie.isFavourite

            if (movie.isFavourite) {
                binding.like.setBackgroundResource(R.drawable.favourite)
            } else {
                binding.like.setBackgroundResource(R.drawable.favourite_border)
            }
        }

        binding.delete.setOnClickListener {
            movies.removeAt(position)
            notifyDataSetChanged()
        }

        return binding.root
    }
}