package com.anggitlearn.movieapp.modules.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggitlearn.movieapp.R
import com.anggitlearn.movieapp.adapter.AllMovieAdapter
import com.anggitlearn.movieapp.model.FilmModel
import com.anggitlearn.movieapp.modules.details.DetailActivity
import kotlinx.android.synthetic.main.content_all_movie.*


class MovieListActivity : AppCompatActivity() {
    private var dataList = ArrayList<FilmModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        //actionbar
        val actionbar = supportActionBar
        //set back button
        actionbar!!.setDisplayHomeAsUpEnabled(true)

        dataList = intent.getParcelableArrayListExtra("data")

        rv_all_movie.layoutManager = LinearLayoutManager(this)

        rv_all_movie.adapter =
            AllMovieAdapter(dataList) {
                val intent = Intent(this, DetailActivity::class.java)
                    .putExtra("data", it)
                startActivity(intent)
            }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}