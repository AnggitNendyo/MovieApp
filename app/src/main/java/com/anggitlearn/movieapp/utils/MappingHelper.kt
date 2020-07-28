package com.anggitlearn.movieapp.utils

import android.database.Cursor
import android.util.EventLogTags
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.DESCRIPTION
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.GENRE
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.POSTER
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.RATING
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.TITLE
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion.TRAILER
import com.anggitlearn.movieapp.databases.DatabaseContract.NoteColums.Companion._ID
import com.anggitlearn.movieapp.model.FilmModel

object MappingHelper {
    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<FilmModel> {
        val noteList = ArrayList<FilmModel>()
        notesCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(_ID))
                val title = getString(getColumnIndexOrThrow(TITLE))
                val description = getString(getColumnIndexOrThrow(DESCRIPTION))
                val genre = getString(getColumnIndexOrThrow(GENRE))
                val poster = getInt(getColumnIndexOrThrow(POSTER))
                val trailer = getInt(getColumnIndexOrThrow(TRAILER))
                val rating = getFloat(getColumnIndexOrThrow(RATING))
                noteList.add(FilmModel(id, title, description, genre, poster, trailer, rating))
            }
        }
        return noteList
    }
}