package com.abedfattal.rexapixakt.models.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abedfattal.rexapixakt.framework.db.USERS_TABLE
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
@Entity(tableName = USERS_TABLE)
data class User(
    @PrimaryKey
    val email: String,
    val password: String,
    val age: Int?
) {
    fun toJson(): String = Json.encodeToString(serializer(),this)

    companion object {
        fun fromJson(jsonUser:String): User {
            return Json.decodeFromString(serializer(),jsonUser)
        }
    }
}