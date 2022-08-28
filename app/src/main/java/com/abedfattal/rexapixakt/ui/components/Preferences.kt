package com.abedfattal.rexapixakt.ui.components

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.abedfattal.rexapixakt.RexaPixaApplication.Companion.app
import com.abedfattal.rexapixakt.models.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object Preferences {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

    private val USERS_KEY = stringPreferencesKey("saved_user")

    val savedUser: Flow<User?> = app.dataStore.data.map {
        val jsonUser = it[USERS_KEY]
        if (jsonUser != null && jsonUser.isNotEmpty()) User.fromJson(jsonUser)
        else null
    }

    suspend fun saveUser(user: User?) {
        app.dataStore.edit {
            it[USERS_KEY] = user?.toJson() ?: ""
        }
    }
}

