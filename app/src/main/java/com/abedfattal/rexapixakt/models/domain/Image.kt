package com.abedfattal.rexapixakt.models.domain

import com.google.gson.annotations.SerializedName

data class Image(
    val id: Int,
    val type: String,
    val tags: String,
    @SerializedName("webformatURL")
    val image: String,
    val largeImageURL: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val views: Int,
    val downloads: Int,
    val comments: Int,
    val likes: Int,
    val user: String,
    val userImageURL: String,
)