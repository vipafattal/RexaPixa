package com.abedfattal.rexapixakt.framework.di

import com.abedfattal.rexapixakt.framework.di.modules.*

object AppKoinService {

    val modules = listOf(
        webServicesModule,
        databasesModule,
        dataSourcesModule,
        repositoriesModule,
        viewModelsModule
    )

}