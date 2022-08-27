package com.abedfattal.rexapixakt.framework.webservice.user

import com.abedfattal.rexapixakt.framework.db.MockServerDao
import com.abedfattal.rexapixakt.framework.exception.EmailAlreadyExistException
import com.abedfattal.rexapixakt.framework.exception.UserNotFoundException
import com.abedfattal.rexapixakt.models.domain.User
import kotlinx.coroutines.delay
import retrofit2.Response


class UserMockService(private val dao: MockServerDao) {

    suspend fun login(email: String, password: String): Response<User> {
        delay(750L)

        val user = dao.getUser(email, password)

        return if (user != null) {
            Response.success(user)
        } else throw UserNotFoundException()

    }

    suspend fun register(user: User): Response<Unit> {
        delay(750L)
        return if (!isEmailExist(user.email)) {
            dao.insertUser(user)
            Response.success(Unit)
        } else
            throw EmailAlreadyExistException()
    }

    private suspend fun isEmailExist(email: String): Boolean {
        return dao.isEmailExist(email) != null
    }

}