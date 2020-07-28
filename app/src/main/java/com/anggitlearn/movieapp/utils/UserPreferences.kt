package com.anggitlearn.movieapp.utils

import android.content.Context
import com.anggitlearn.movieapp.utils.Const.NAMA_USER
import com.anggitlearn.movieapp.utils.Const.PREFS_NAME
import com.anggitlearn.movieapp.utils.Const.STATUS_USER


class UserPreferences(context: Context){
    private val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setStatusUser(value: Boolean){
        val editor = preference.edit()
        editor.putBoolean(STATUS_USER, value)
        editor.apply()
    }

    fun setNamaUser(value: String){
        val editor = preference.edit()
        editor.putString(NAMA_USER, value)
        editor.apply()
    }

    fun getStatusUser() :Boolean{
        return preference.getBoolean(STATUS_USER, false)
    }

    fun getNamaUser() : String?{
        return preference.getString(NAMA_USER,"")
    }
}