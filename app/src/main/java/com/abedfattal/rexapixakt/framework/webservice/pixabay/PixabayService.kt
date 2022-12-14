package com.abedfattal.rexapixakt.framework.webservice.pixabay

import com.abedfattal.rexapixakt.framework.TOKEN
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {

    companion object {
        const val PAGE_SIZE = 20
    }

    @GET("api")
    suspend fun getImages(
        @Query("key")
        key: String = TOKEN,
        @Query("q")
        search: String? = null,
        @Query("image_type")
        imageType: String = "Photo",
        @Query("per_page")
        perPage: Int = PAGE_SIZE,
        @Query("safesearch")
        safeMode: Boolean = true,
        @Query("page")
        page: Int
    ): Response<PixabayModels.ImagesResult>

}