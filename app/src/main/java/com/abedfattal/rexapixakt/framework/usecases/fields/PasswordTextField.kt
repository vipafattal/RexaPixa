package com.abedfattal.rexapixakt.framework.usecases.fields

import com.abedfattal.rexapixakt.R
import com.abedfattal.rexapixakt.framework.usecases.TextField
import com.abedfattal.rexapixakt.utils.stringify

class PasswordTextField : TextField() {
    override val isRequired: Boolean = true

    override fun validation(text: String): String? {
        val isValidEmail = text.length in 6..12
        return if (isValidEmail) null
        else stringify(R.string.invalid_password)
    }
}