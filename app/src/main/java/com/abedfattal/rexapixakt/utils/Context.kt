package com.abedfattal.rexapixakt.utils

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import androidx.annotation.AttrRes
import java.util.*


inline fun <reified T : Any> Context.launchActivity() {
    val intent = newIntent<T>()
    startActivity(intent)
}

inline fun <reified T : Any> Context.newIntent(): Intent = Intent(this, T::class.java)



