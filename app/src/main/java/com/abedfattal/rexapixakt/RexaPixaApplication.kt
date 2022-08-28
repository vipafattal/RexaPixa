package com.abedfattal.rexapixakt

import android.app.Application
import com.abedfattal.rexapixakt.framework.di.AppKoinService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RexaPixaApplication : Application() {

    companion object {
        lateinit var app: RexaPixaApplication
            private set

    }

    override fun onCreate() {
        super.onCreate()
        app = this
        startKoinService()
    }

    private fun startKoinService() {

        startKoin {
            androidLogger()
            androidContext(this@RexaPixaApplication)
            modules(AppKoinService.modules)
        }
    }

}