package com.abedfattal.rexapixakt.ui.newuser.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.abedfattal.rexapixakt.framework.data.user.UserRepository
import com.abedfattal.rexapixakt.framework.usecases.fields.AgeTextField
import com.abedfattal.rexapixakt.framework.usecases.fields.EmailTextField
import com.abedfattal.rexapixakt.framework.usecases.fields.PasswordTextField
import com.abedfattal.rexapixakt.framework.utils.doOnSuccess
import com.abedfattal.rexapixakt.models.ProcessState
import com.abedfattal.rexapixakt.models.domain.User
import com.abedfattal.rexapixakt.tryParse
import com.abedfattal.rexapixakt.ui.components.FormViewModel
import com.abedfattal.rexapixakt.ui.components.Preferences
import com.abedfattal.rexapixakt.utils.Event
import com.abedfattal.rexapixakt.utils.addSources
import kotlinx.coroutines.flow.Flow

class RegisterViewModel(private val userRepository: UserRepository) : FormViewModel() {

    val emailTextField = EmailTextField()
    val passwordTextField = PasswordTextField()
    val ageTextField = AgeTextField()

    override val isFormValid: LiveData<Boolean> = MediatorLiveData<Boolean>().addSources(
        emailTextField.field,
        passwordTextField.field,
        ageTextField.field,
    ) {
        emailTextField.isValid && passwordTextField.isValid && ageTextField.isValid
    }

    val popBackEvent: LiveData<Event<Unit>> get() = _popBackEvent
    private val _popBackEvent: MutableLiveData<Event<Unit>> = MutableLiveData()

    override suspend fun onSubmit(): Flow<ProcessState<Unit>> {
        val user = User(
            email = emailTextField.field.value!!,
            password = passwordTextField.field.value!!,
            age = ageTextField.field.value?.tryParse()
        )

       return userRepository.register(user).doOnSuccess {
            Preferences.saveUser(user)
        }
    }

    fun pop() {
        _popBackEvent.value = Event(Unit)
    }


}


