package com.abedfattal.rexapixakt.ui.newuser.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.abedfattal.rexapixakt.framework.data.user.UserRepository
import com.abedfattal.rexapixakt.framework.usecases.fields.EmailTextField
import com.abedfattal.rexapixakt.framework.usecases.fields.PasswordTextField
import com.abedfattal.rexapixakt.framework.utils.doOnSuccess
import com.abedfattal.rexapixakt.models.ProcessState
import com.abedfattal.rexapixakt.ui.components.FormViewModel
import com.abedfattal.rexapixakt.ui.components.Preferences
import com.abedfattal.rexapixakt.utils.Event
import com.abedfattal.rexapixakt.utils.addSources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginViewModel(private val userRepository: UserRepository) : FormViewModel() {



    val emailTextField = EmailTextField()
    val passwordTextField = PasswordTextField()

    override val isFormValid = MediatorLiveData<Boolean>().addSources(
        emailTextField.field,
        passwordTextField.field
    ) {
        emailTextField.isValid && passwordTextField.isValid
    }

    val popBackEvent: LiveData<Event<Unit>> get() = _popBackEvent
    private val _popBackEvent: MutableLiveData<Event<Unit>> = MutableLiveData()

    override suspend fun onSubmit(): Flow<ProcessState<Unit>> {
        return userRepository.login(
            email = emailTextField.field.value!!,
            password = passwordTextField.field.value!!,
        ).doOnSuccess { user ->
            Preferences.saveUser(user!!)
        }.map { it.transformProcessType() }
    }

    fun pop() {
        _popBackEvent.value = Event(Unit)
    }
}


