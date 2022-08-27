package com.abedfattal.rexapixakt.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun longToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun longToast(context: Context, @StringRes text: Int) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun shortToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}

fun shortToast(context: Context, @StringRes text: Int) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}