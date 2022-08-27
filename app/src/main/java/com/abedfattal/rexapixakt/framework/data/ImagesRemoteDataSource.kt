package com.abedfattal.rexapixakt.framework.data

import com.abedfattal.rexapixakt.framework.utils.newRequest
import com.abedfattal.rexapixakt.framework.utils.transform
import com.abedfattal.rexapixakt.framework.webservice.pixabay.PixabayService
import com.abedfattal.rexapixakt.models.ProcessState
import com.abedfattal.rexapixakt.models.domain.Image
import kotlinx.coroutines.flow.Flow

class ImagesRemoteDataSource(private val pixabayService: PixabayService) {

    fun getImages(): Flow<ProcessState<List<Image>>> {
        return newRequest { pixabayService.getImages() }.transform { imagesResult ->
            imagesResult.images
        }
    }

}