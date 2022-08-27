package com.abedfattal.rexapixakt.framework.di.modules

import com.abedfattal.rexapixakt.framework.BASE_URL
import com.abedfattal.rexapixakt.framework.webservice.pixabay.PixabayService
import com.abedfattal.rexapixakt.framework.webservice.user.UserMockService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val webServicesModule = module {
    single {
        buildDefaultRetrofitService(BASE_URL, clazz = PixabayService::class.java)
    }

    single {
        UserMockService(dao = get())
    }
}

private fun <T> buildDefaultRetrofitService(baseUrl: String, clazz: Class<T>): T {
    val logger =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .retryOnConnectionFailure(true)
        .build()

    return Retrofit.Builder().baseUrl(baseUrl).client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(clazz)
}
