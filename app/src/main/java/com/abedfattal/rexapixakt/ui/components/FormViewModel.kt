package com.abedfattal.rexapixakt.ui.components

import androidx.lifecycle.*
import com.abedfattal.rexapixakt.models.ProcessState
import com.abedfattal.rexapixakt.utils.Event
import com.abedfattal.rexapixakt.utils.addSources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class FormViewModel : ViewModel() {

    val closeKeyboardEvent: LiveData<Event<Unit>> get() = _closeKeyboardEvent
    private val _closeKeyboardEvent: MutableLiveData<Event<Unit>> = MutableLiveData()

    protected abstract val isFormValid: LiveData<Boolean>

    val submitProcess: LiveData<ProcessState<Unit>> get() = _submitProcess
    private val _submitProcess = MutableLiveData<ProcessState<Unit>>()

    val isSubmittingForm =
        Transformations.map(_submitProcess) { process -> process != null && process is ProcessState.Loading }

    val canSubmitForm by lazy {
        MediatorLiveData<Boolean>().addSources(
            isSubmittingForm,
            isFormValid
        ) { (processingRegister, isFormValid) ->

            val isNotProcessingRegister = !(processingRegister ?: false)
            (isFormValid ?: false) && isNotProcessingRegister
        }
    }

    fun submit() {
        _closeKeyboardEvent.value = Event(Unit)
        viewModelScope.launch(Dispatchers.IO) {
            onSubmit().collect {
                _submitProcess.postValue(it)
            }
        }
    }

    protected abstract suspend fun onSubmit(): Flow<ProcessState<Unit>>

}