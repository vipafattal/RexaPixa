package com.abedfattal.rexapixakt.ui.imagedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abedfattal.rexapixakt.utils.Event

class ImageDetailsViewModel : ViewModel() {

    val popBackEvent: LiveData<Event<Unit>> get() = _popBackEvent
    private val _popBackEvent: MutableLiveData<Event<Unit>> = MutableLiveData()

    fun pop() {
        _popBackEvent.value = Event(Unit)
    }

}