package com.abedfattal.rexapixakt.framework.di.modules

import androidx.room.Room
import com.abedfattal.rexapixakt.RexaPixaApplication
import com.abedfattal.rexapixakt.framework.db.MockServerDatabase
import org.koin.dsl.module

val databasesModule = module {
    single {
        val databaseBuilder = Room.databaseBuilder(
            RexaPixaApplication.app,
            MockServerDatabase::class.java,
            MockServerDatabase.DATABASE_NAME
        )
        databaseBuilder.build().getUserMockRemoteDao()
    }
}
