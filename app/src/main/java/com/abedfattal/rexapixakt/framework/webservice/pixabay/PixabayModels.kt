package com.abedfattal.rexapixakt.framework.webservice.pixabay

import com.abedfattal.rexapixakt.models.domain.Image
import com.google.gson.annotations.SerializedName

object PixabayModels {

    data class ImagesResult(
        val total: Int,
        @SerializedName("totalHits")
        val totalImages: Int,
        @SerializedName("hits")
        val images: List<Image>
    )

}