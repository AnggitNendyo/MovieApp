package com.anggitlearn.movieapp.modules.wishlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggitlearn.movieapp.R
import com.anggitlearn.movieapp.adapter.AllMovieAdapter
import com.anggitlearn.movieapp.databases.MovieHelper
import com.anggitlearn.movieapp.model.FilmModel
import com.anggitlearn.movieapp.modules.details.DetailActivity
import com.anggitlearn.movieapp.utils.MappingHelper
import kotlinx.android.synthetic.main.content_all_movie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class WishListActivity : AppCompatActivity() {
    private var dataList = ArrayList<FilmModel>()
    lateinit var noteHelper: MovieHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        noteHelper = MovieHelper.getInstance(this)
        noteHelper.open()
        getData()

        //actionbar
        val actionbar = supportActionBar
        //set back button
        actionbar!!.setDisplayHomeAsUpEnabled(true)
    }
        override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun getData(){
        GlobalScope.launch(Dispatchers.Main) {
            val defferedValue = async(Dispatchers.IO) {
                var cursor = noteHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
            dataList = defferedValue.await()
            initView()
        }
    }

    fun initView(){
        rv_all_movie.layoutManager = LinearLayoutManager(this)

        rv_all_movie.adapter =
            AllMovieAdapter(dataList) {
                val intent = Intent(this, DetailActivity::class.java)
                    .putExtra("data", it)
                startActivity(intent)
            }
    }
}