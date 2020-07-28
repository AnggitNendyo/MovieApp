package com.anggitlearn.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.anggitlearn.movieapp.model.FilmModel
import com.anggitlearn.movieapp.R
import com.bumptech.glide.Glide

class AllMovieAdapter (private var data: List<FilmModel>,
                       private val listener: (FilmModel) -> Unit)
    : RecyclerView.Adapter<AllMovieAdapter.MovieViewHolder>(){

    lateinit var ContextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        ContextAdapter = parent.context

        val inflatedView: View = layoutInflater.inflate(R.layout.item_movie_vertical, parent, false)
        return MovieViewHolder(
            inflatedView
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int){
        holder.bindItem(data[position], listener, ContextAdapter, position )
    }

    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val tvTitle : TextView = view.findViewById(R.id.tv_title)
        private val ivPoster : ImageView = view.findViewById(R.id.iv_banner)
        private val tvDescription : TextView = view.findViewById(R.id.tv_deskripsi)
        private val rbMovie : RatingBar = view.findViewById(R.id.ratingBar)

        fun bindItem(data: FilmModel, listener: (FilmModel) -> Unit, context: Context, position: Int){
            tvTitle.text = data.judul
            tvDescription.text = data.deskripsi
            Glide.with(context).load(data.poster).into(ivPoster)

            rbMovie.rating = data.rating

            itemView.setOnClickListener{
                listener(data)

            }
        }
    }

}