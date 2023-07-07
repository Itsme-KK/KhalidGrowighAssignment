package com.example.growighassignment

import retrofit2.Call
import retrofit2.http.GET

interface APIDataInterface {
    @GET("/users")
    fun getData(): Call<List<ImageData>>
}