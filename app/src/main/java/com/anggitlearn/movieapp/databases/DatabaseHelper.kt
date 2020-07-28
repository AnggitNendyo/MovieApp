package com.anggitlearn.movieapp.databases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.DESCRIPTION
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.GENRE
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.POSTER
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.RATING
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.TABLE_NAME
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.TITLE
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.TRAILER
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion._ID

internal class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context,
        DATABASE_NAME, null,
        DATABASE_VERSION
    ) {

    companion object {
        private const val DATABASE_NAME = "dbmovie_app"
        private const val DATABASE_VERSION = 1
        private val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
                " (${_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${TITLE} TEXT NOT NULL," +
                " ${DESCRIPTION} TEXT NOT NULL," +
                " ${GENRE} TEXT NOT NULL," +
                " ${POSTER} TEXT NOT NULL," +
                " ${TRAILER} TEXT NOT NULL," +
                " ${RATING} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase , p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}