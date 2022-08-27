package com.abedfattal.rexapixakt.framework.data

import com.abedfattal.rexapixakt.framework.webservice.pixabay.PixabayModels
import com.abedfattal.rexapixakt.models.ProcessState
import com.abedfattal.rexapixakt.models.domain.Image
import kotlinx.coroutines.flow.Flow

class ImagesRepository(private val remoteDataSource: ImagesRemoteDataSource) {

    fun getImages(): Flow<ProcessState<List<Image>>> {
        return  remoteDataSource.getImages()
    }

}