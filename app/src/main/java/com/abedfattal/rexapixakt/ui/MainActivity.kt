package com.abedfattal.rexapixakt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.abedfattal.rexapixakt.R
import com.abedfattal.rexapixakt.ui.components.Preferences
import com.abedfattal.rexapixakt.ui.newuser.NewUserActivity
import com.abedfattal.rexapixakt.utils.launchActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Preferences.savedUser.asLiveData().observe(this) { user ->
            if (user == null) {
                launchActivity<NewUserActivity>()
                finish()
            }
        }

    }

}