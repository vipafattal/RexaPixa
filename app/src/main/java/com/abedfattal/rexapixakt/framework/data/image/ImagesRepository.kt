package com.abedfattal.rexapixakt.framework.data.image

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.abedfattal.rexapixakt.framework.webservice.pixabay.PixabayService
import com.abedfattal.rexapixakt.models.domain.Image
import kotlinx.coroutines.flow.Flow

class ImagesRepository(private val remoteDataSource: ImagesRemoteDataSource) {

    fun getImages(): Flow<PagingData<Image>> {
        return Pager(
            pagingSourceFactory = { remoteDataSource },
            config = PagingConfig(pageSize = PixabayService.PAGE_SIZE)
        ).flow
    }

}