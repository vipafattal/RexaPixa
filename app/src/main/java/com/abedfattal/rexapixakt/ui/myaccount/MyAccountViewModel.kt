package com.abedfattal.rexapixakt.ui.myaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.abedfattal.rexapixakt.ui.components.Preferences
import kotlinx.coroutines.launch

class MyAccountViewModel : ViewModel() {

    fun getUser() = Preferences.savedUser.asLiveData()

    fun logout() {
        viewModelScope.launch {
            Preferences.saveUser(null)
        }
    }

}