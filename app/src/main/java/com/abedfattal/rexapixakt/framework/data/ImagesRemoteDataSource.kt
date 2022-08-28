package com.abedfattal.rexapixakt.framework.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.abedfattal.rexapixakt.framework.utils.newRequest
import com.abedfattal.rexapixakt.framework.utils.onComplete
import com.abedfattal.rexapixakt.framework.utils.transform
import com.abedfattal.rexapixakt.framework.webservice.pixabay.PixabayService
import com.abedfattal.rexapixakt.models.ProcessState
import com.abedfattal.rexapixakt.models.domain.Image
import com.abedfattal.rexapixakt.utils.stringify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ImagesRemoteDataSource(private val pixabayService: PixabayService) :
    PagingSource<Int, Image>() {

    companion object {
        private const val START_AT_PAGE = 1

    }

    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {

        val page = params.key ?: START_AT_PAGE
        val imagesProcess = getImages(page).onComplete.first()

        return if (imagesProcess is ProcessState.Success)
            LoadResult.Page(
                data = imagesProcess.data!!,
                prevKey = null, // Only going forward
                nextKey = page + 1
            )
        else {
            val errorMessage = (imagesProcess as ProcessState.Failed).friendlyMsg
            LoadResult.Error(Exception(stringify(errorMessage)))
        }
    }

    private fun getImages(page: Int): Flow<ProcessState<List<Image>>> {
        return newRequest { pixabayService.getImages(page = page) }.transform { imagesResult ->
            imagesResult.images
        }
    }

}