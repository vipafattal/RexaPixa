package com.abedfattal.rexapixakt.framework.usecases.fields

import com.abedfattal.rexapixakt.R
import com.abedfattal.rexapixakt.framework.usecases.TextField
import com.abedfattal.rexapixakt.tryParse
import com.abedfattal.rexapixakt.utils.stringify

class AgeTextField : TextField() {
    override val isRequired: Boolean = false

    override fun validation(text: String): String? {
        val age: Int? = text.tryParse()
        return if (age != null && age !in 18..99) stringify(R.string.invalid_age)
        else return null
    }
}