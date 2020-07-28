package com.anggitlearn.movieapp.modules.home

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggitlearn.movieapp.modules.list.MovieListActivity
import com.anggitlearn.movieapp.R
import com.anggitlearn.movieapp.adapter.MovieAdapter
import com.anggitlearn.movieapp.api.DummyData
import com.anggitlearn.movieapp.model.FilmModel
import com.anggitlearn.movieapp.modules.details.DetailActivity
import com.anggitlearn.movieapp.modules.login.LoginActivity
import com.anggitlearn.movieapp.modules.wishlist.WishListActivity
import com.anggitlearn.movieapp.utils.Const
import com.anggitlearn.movieapp.utils.Const.CODE_LOGIN
import com.anggitlearn.movieapp.utils.UserPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dataList = ArrayList<FilmModel>()
    lateinit var userPreference: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userPreference = UserPreferences(this)
        initView()

        rv_shimmer.startShimmerAnimation()

        var handler = Handler()
        handler.postDelayed({
            rv_shimmer.stopShimmerAnimation()
            rv_shimmer.visibility = View.GONE
            rv_movie.visibility = View.VISIBLE
            initListener()
            getData()
        }, 3000)
    }

    private fun initListener() {
        tv_login.setOnClickListener{
            if (userPreference.getStatusUser()){
                startActivity(Intent(this, WishListActivity::class.java))
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivityForResult(intent, CODE_LOGIN)
            }
        }
    }

    private fun initView() {
        rv_movie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_movie.adapter =
            MovieAdapter(dataList) {
                val intent = Intent(this, DetailActivity::class.java)
                    .putExtra("data", it)
                startActivity(intent)
            }

        tv_view_all.setOnClickListener {
            val intent = Intent(this, MovieListActivity::class.java)
                .putExtra("data", dataList)
            startActivity(intent)
        }

        if (userPreference.getStatusUser()){
            tv_login.text = userPreference.getNamaUser()
            tv_desc.text = "Thank for join,  do you want to exit ?"
            iv_logout.visibility = View.VISIBLE
        }else{
            tv_login.text = "Login"
            tv_desc.text = "Save your favorite movie"
            iv_logout.visibility = View.INVISIBLE
        }
    }

    fun getData(){
        for (i in DummyData.titleMovie.indices){
            dataList.add(
                FilmModel(
                    i+1,
                    DummyData.titleMovie[i],
                    DummyData.descMovie[i],
                    DummyData.genreMovie[i],
                    DummyData.posterMovie[i],
                    DummyData.trailerMovie[i],
                    DummyData.ratingMovie[i]
                )
            )
        }
        initView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Const.CODE_LOGIN){
            if (resultCode == Activity.RESULT_OK){
                startActivity(Intent(this, WishListActivity::class.java))
                initView()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        initView()
    }
}