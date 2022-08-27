package com.abedfattal.rexapixakt.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abedfattal.rexapixakt.models.domain.User


/**
 [MockServerDatabase] is database consumed by the fake user service.
 **/

@Database(
    entities = [User::class],
    version = MockServerDatabase.DATA_BASE_VERSION,
)
abstract class MockServerDatabase : RoomDatabase() {
    companion object {
        const val DATA_BASE_VERSION = 1
        const val DATABASE_NAME = "mock-users-db"
    }

    abstract fun getUserMockRemoteDao(): MockServerDao
}