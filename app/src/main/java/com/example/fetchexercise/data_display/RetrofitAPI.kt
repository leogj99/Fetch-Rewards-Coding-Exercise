package com.example.fetchexercise.data_display

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("hiring.json")
    fun getData(): Call<List<Record>>

}