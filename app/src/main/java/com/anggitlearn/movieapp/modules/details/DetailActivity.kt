package com.anggitlearn.movieapp.modules.details

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.anggitlearn.movieapp.R
import com.anggitlearn.movieapp.adapter.MovieAdapter
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.DESCRIPTION
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.GENRE
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.POSTER
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.RATING
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.TITLE
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.TRAILER
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion._ID
import com.anggitlearn.movieapp.databases.MovieHelper
import com.anggitlearn.movieapp.model.FilmModel
import com.anggitlearn.movieapp.modules.login.LoginActivity
import com.anggitlearn.movieapp.utils.Const.CODE_LOGIN
import com.anggitlearn.movieapp.utils.UserPreferences
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    lateinit var data: FilmModel
    lateinit var noteHelper: MovieHelper
    private var values = ContentValues()
    private var statusFavorite = false

    lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        data = intent.getParcelableExtra<FilmModel>("data")

        userPreferences = UserPreferences(this)
        noteHelper = MovieHelper.getInstance(this)
        noteHelper.open()

        initView()
        initListener()
    }

    fun initView() {
        tv_title.setText(data.judul)
        tv_genre.setText(data.genre)
        tv_description.setText(data.deskripsi)

        statusFavorite()

    }

    fun initListener() {
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + data.trailer))
        videoView.start()

        iv_favorite.setOnClickListener {
            if (userPreferences.getStatusUser()) {
                if (statusFavorite) {
                    noteHelper.deleteById(data.id.toString())
                    iconFavorite(false)

                } else {
                    values.put(_ID, data.id)
                    values.put(TITLE, data.judul)
                    values.put(DESCRIPTION, data.deskripsi)
                    values.put(GENRE, data.genre)
                    values.put(POSTER, data.poster)
                    values.put(TRAILER, data.trailer)
                    values.put(RATING, data.rating)

                    noteHelper.insert(values)
                    iconFavorite(true)
                }
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivityForResult(intent, CODE_LOGIN)
            }
        }
    }

    fun iconFavorite(boolean: Boolean) {
        if (boolean) {
            statusFavorite = true
            iv_favorite.setImageResource(R.drawable.ic_baseline_favorite_enable)
        } else {
            statusFavorite = false
            iv_favorite.setImageResource(R.drawable.ic_baseline_favorite_disable)
        }
    }

    fun statusFavorite() {
        var cursor = noteHelper.queryById(data.id.toString())
        if (cursor.moveToNext()) {
            iconFavorite(true)
        } else {
            iconFavorite(false)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, bundle: Intent?) {
        super.onActivityResult(requestCode, resultCode, bundle)

        if (requestCode == CODE_LOGIN) {
            if (resultCode == Activity.RESULT_OK) {
                if (statusFavorite) {
                    noteHelper.deleteById(data.id.toString())
                    iconFavorite(false)
                } else {
                    values.put(_ID, data.id)
                    values.put(TITLE, data.judul)
                    values.put(DESCRIPTION, data.deskripsi)
                    values.put(GENRE, data.genre)
                    values.put(POSTER, data.poster)
                    values.put(TRAILER, data.trailer)
                    values.put(RATING, data.rating)

                    noteHelper.insert(values)
                    iconFavorite(true)
                }
            }
        }
    }
}