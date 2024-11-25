package com.example.a2kotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// API Interface
interface GiphyApi {
    @GET("random?api_key=Koj7rrZfxPMmbim30u9gY4okmFmMmCqT&tag=&rating=g")
    suspend fun getRandomGif(): GifResponse
}

// Data models
data class GifResponse(val data: GifData)
data class GifData(val images: GifImages)
data class GifImages(val original: GifOriginal)
data class GifOriginal(val url: String)

object GiphyApiService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.giphy.com/v1/gifs/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: GiphyApi = retrofit.create(GiphyApi::class.java)

    suspend fun fetchGifFromApi(): String {
        try {
            val response = api.getRandomGif()
            return response.data.images.original.url
        } catch (e: Exception) {
            throw e
        }
    }
}