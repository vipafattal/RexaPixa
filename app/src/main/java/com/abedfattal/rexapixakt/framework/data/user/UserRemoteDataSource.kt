package com.abedfattal.rexapixakt.framework.data.user

import com.abedfattal.rexapixakt.R
import com.abedfattal.rexapixakt.framework.exception.EmailAlreadyExistException
import com.abedfattal.rexapixakt.framework.exception.UserNotFoundException
import com.abedfattal.rexapixakt.framework.utils.newRequest
import com.abedfattal.rexapixakt.framework.webservice.user.UserMockService
import com.abedfattal.rexapixakt.models.ProcessState
import com.abedfattal.rexapixakt.models.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class UserRemoteDataSource(private val userService: UserMockService) {

    suspend fun register(user: User): Flow<ProcessState<Unit>> = flow {
         try {
            emitAll(newRequest { userService.register(user) })
        } catch (e: EmailAlreadyExistException) {
            emit(ProcessState.Failed(error = e.message, R.string.email_already_exist))
        }
    }

    suspend fun login(email: String, password: String): Flow<ProcessState<User>> = flow {
        try {
            emitAll(newRequest { userService.login(email, password) })
        } catch (e: UserNotFoundException) {
            emit(ProcessState.Failed(error = e.message, R.string.user_not_found))
        }
    }


}