package com.abedfattal.rexapixakt.framework.di.modules

import com.abedfattal.rexapixakt.framework.data.ImagesRemoteDataSource
import com.abedfattal.rexapixakt.framework.data.ImagesRepository
import com.abedfattal.rexapixakt.framework.data.user.UserRemoteDataSource
import com.abedfattal.rexapixakt.framework.data.user.UserRepository
import org.koin.dsl.module

val dataSourcesModule = module {
    single {
        UserRemoteDataSource(userService = get())
    }
    single {
        ImagesRemoteDataSource(pixabayService = get())
    }
}

val repositoriesModule = module {
    single {
        UserRepository(remoteDataSource = get())
    }
    single {
        ImagesRepository(remoteDataSource = get())
    }
}