package ru.alexeypostnov.lesson11.data.network

import android.graphics.Bitmap
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

// Retrofit
interface MyApi {
    fun callApi(): String

    @GET("/camera/snapshot")
    suspend fun getImage(): Response<ResponseBody>
}