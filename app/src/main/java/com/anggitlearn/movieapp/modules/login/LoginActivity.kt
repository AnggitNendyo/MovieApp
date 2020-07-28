package com.anggitlearn.movieapp.modules.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anggitlearn.movieapp.R
import com.anggitlearn.movieapp.utils.UserPreferences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var userPreference: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userPreference = UserPreferences(this)

        btn_login.setOnClickListener{
            if (et_user_name.text.isNullOrEmpty()){
                et_user_name.error = "Please input username!"
            }else{
                userPreference.setNamaUser(et_user_name.text.toString())
                userPreference.setStatusUser(true)

                var resultIntent = Intent()
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}