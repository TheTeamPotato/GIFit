package com.theteampotato.gifit.data.remote.model

@kotlinx.serialization.Serializable
data class GiphyResult(val data: List<GiphyData>)

@kotlinx.serialization.Serializable
data class GiphyData(val images: GiphyImages)

@kotlinx.serialization.Serializable
data class GiphyImages(val original: GiphyImage)

@kotlinx.serialization.Serializable
data class GiphyImage(val url: String)