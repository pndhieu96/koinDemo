package com.example.koindemo

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("books/v1/volumes?q=\"\"")
    fun getBooks(): Call<ResponseBody>
}