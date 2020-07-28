package com.anggitlearn.movieapp.databases

import android.provider.BaseColumns

internal class DatabaseContract{

    internal class NoteColums : BaseColumns {
        companion object{
            const val TABLE_NAME = "note"
            const val _ID = "id"
            const val TITLE = "title"
            const val DESCRIPTION = "description"
            const val GENRE = "genre"
            const val POSTER = "poster"
            const val TRAILER = "trailer"
            const val RATING = "rating"
        }
    }
}