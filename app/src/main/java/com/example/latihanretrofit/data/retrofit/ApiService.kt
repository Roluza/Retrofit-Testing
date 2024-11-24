package com.example.latihanretrofit.data.retrofit


import com.example.latihanretrofit.data.response.PhotoResponseItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("photos")
    fun getPhotos(): Call<List<PhotoResponseItem>>
}