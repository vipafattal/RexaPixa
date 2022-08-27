package com.abedfattal.rexapixakt.framework.usecases

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.abedfattal.rexapixakt.R
import com.abedfattal.rexapixakt.tryParse
import com.abedfattal.rexapixakt.utils.stringify

abstract class TextField {
    abstract val isRequired: Boolean
    var isValid: Boolean = false
        private set
    val field: MutableLiveData<String?> = MutableLiveData(null)

    val errorMessage: LiveData<String?> = Transformations.map(field) { text ->
        if (text == null) {
            isValid = !isRequired
            null
        } else if (isRequired && text.isBlank()) {
            isValid = false
            stringify(R.string.field_required)
        } else {
            val errorMsg = validation(text)
            isValid = errorMsg == null
            errorMsg
        }
    }

    protected abstract fun validation(text: String): String?
}





