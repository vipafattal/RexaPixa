package com.abedfattal.rexapixakt.framework.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abedfattal.rexapixakt.framework.db.USERS_TABLE
import com.abedfattal.rexapixakt.models.domain.User


@Dao
interface MockServerDao {

    @Query("SELECT email FROM $USERS_TABLE WHERE email = :email LIMIT 1")
    suspend fun isEmailExist(email: String): String?

    @Query("SELECT * FROM $USERS_TABLE WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUser(email: String, password: String): User?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)


}