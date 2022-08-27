package com.abedfattal.rexapixakt.framework.usecases.fields

import android.util.Patterns
import com.abedfattal.rexapixakt.R
import com.abedfattal.rexapixakt.framework.usecases.TextField
import com.abedfattal.rexapixakt.utils.stringify

class EmailTextField : TextField() {
    override val isRequired: Boolean = true

    override fun validation(text: String): String? {
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(text).matches()
        return if (isValidEmail) null
        else stringify(R.string.invalid_email)
    }
}