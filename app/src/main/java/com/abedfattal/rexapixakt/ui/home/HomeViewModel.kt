package com.abedfattal.rexapixakt.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.abedfattal.rexapixakt.framework.data.ImagesRepository

class HomeViewModel(private val imagesRepository: ImagesRepository) : ViewModel() {

    fun getImages() = imagesRepository.getImages().cachedIn(viewModelScope)


}