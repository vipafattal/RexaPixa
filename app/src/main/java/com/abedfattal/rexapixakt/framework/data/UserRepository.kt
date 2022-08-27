package com.abedfattal.rexapixakt.framework.data

import com.abedfattal.rexapixakt.models.ProcessState
import com.abedfattal.rexapixakt.models.domain.User
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val remoteDataSource: UserRemoteDataSource
) {

    suspend fun register(user: User): Flow<ProcessState<Unit>> = remoteDataSource.register(user)
    suspend fun login(email: String, password:String): Flow<ProcessState<User>> = remoteDataSource.login(email,password)

}