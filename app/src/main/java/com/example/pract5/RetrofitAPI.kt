package com.example.pract5

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {
    @GET("8ad4-074b-478c-bcbd")
    fun getAllUsers(): Call<ArrayList<User>>
}