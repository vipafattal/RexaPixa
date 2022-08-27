package com.abedfattal.rexapixakt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.abedfattal.rexapixakt.framework.data.ImagesRepository
import com.abedfattal.rexapixakt.framework.data.UserRepository

class HomeViewModel(private val imagesRepository: ImagesRepository) : ViewModel() {

    fun getImages() = imagesRepository.getImages().asLiveData()



}